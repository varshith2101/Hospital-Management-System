#!/usr/bin/env zsh
set -e

# ==================================================
# HMS PostgreSQL Database Setup Script (macOS)
# ==================================================

echo ""
echo "🏥 Hospital Management System - Database Setup (macOS)"
echo "====================================================="
echo ""

# ---------- Helpers ----------
error() {
  echo "❌ $1"
  exit 1
}

info() {
  echo "ℹ️  $1"
}

success() {
  echo "✅ $1"
}

# ---------- Check Homebrew ----------
if ! command -v brew >/dev/null 2>&1; then
  error "Homebrew is not installed.
Install it using:
  /bin/bash -c \"\$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)\""
fi

success "Homebrew is installed"

# ---------- Check PostgreSQL ----------
if ! command -v psql >/dev/null 2>&1; then
  echo "❌ PostgreSQL is not installed."
  read "install_pg?📦 Install PostgreSQL 16 now? (y/n): "

  if [[ "$install_pg" =~ ^[Yy]$ ]]; then
    brew install postgresql@16
    brew services start postgresql@16
    sleep 3
  else
    error "Please install PostgreSQL manually:
  brew install postgresql@16
  brew services start postgresql@16"
  fi
fi

success "PostgreSQL is installed"

# ---------- Ensure PostgreSQL is running ----------
if ! pg_isready >/dev/null 2>&1; then
  info "PostgreSQL is not running. Starting service..."
  brew services start postgresql@16
  sleep 3
fi

pg_isready >/dev/null 2>&1 || error "PostgreSQL failed to start"
success "PostgreSQL is running"

# ---------- Database Config ----------
echo ""
echo "Enter database configuration (press Enter for defaults)"
echo ""

read "DB_NAME?Database name [hms_db]: "
DB_NAME=${DB_NAME:-hms_db}

DEFAULT_USER=$(whoami)
read "DB_USER?Username [$DEFAULT_USER]: "
DB_USER=${DB_USER:-$DEFAULT_USER}

read -s "DB_PASS?Password (leave empty for none): "
echo ""

# ---------- Create Database ----------
info "Creating database '$DB_NAME'..."

if [[ -z "$DB_PASS" ]]; then
  createdb -U "$DB_USER" "$DB_NAME" 2>/dev/null || true
else
  PGPASSWORD="$DB_PASS" createdb -U "$DB_USER" "$DB_NAME" 2>/dev/null || true
fi

# ---------- Verify Database ----------
if [[ -z "$DB_PASS" ]]; then
  psql -U "$DB_USER" -lqt | cut -d \| -f 1 | grep -qw "$DB_NAME" \
    && success "Database '$DB_NAME' is ready"
else
  PGPASSWORD="$DB_PASS" psql -U "$DB_USER" -lqt | cut -d \| -f 1 | grep -qw "$DB_NAME" \
    && success "Database '$DB_NAME' is ready"
fi

# ---------- Create .env ----------
info "Creating .env file..."

cat > .env <<EOF
DB_URL=jdbc:postgresql://localhost:5432/$DB_NAME
DB_USERNAME=$DB_USER
DB_PASSWORD=$DB_PASS
EOF

success ".env file created"

# ---------- Create Spring local config ----------
info "Creating application-local.properties..."

mkdir -p src/main/resources

cat > src/main/resources/application-local.properties <<EOF
spring.datasource.url=jdbc:postgresql://localhost:5432/$DB_NAME
spring.datasource.username=$DB_USER
spring.datasource.password=$DB_PASS
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
EOF

success "application-local.properties created"

# ---------- Final Instructions ----------
echo ""
echo "🎉 Setup complete!"
echo ""
echo "Next steps:"
echo ""
echo "1️⃣  Run using local Spring profile:"
echo "    mvn spring-boot:run -Dspring-boot.run.profiles=local"
echo ""
echo "2️⃣  OR load environment variables manually:"
echo "    source <(sed 's/^/export /' .env)"
echo "    mvn spring-boot:run"
echo ""
echo "🚀 PostgreSQL data will persist across restarts."
echo ""
