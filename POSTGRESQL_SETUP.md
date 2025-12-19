# PostgreSQL Setup Guide for HMS (macOS + zsh)

This guide will help you set up PostgreSQL for persistent data storage on macOS using zsh.

## 🚀 Quick Setup (Easiest Way)

**Just run the automated setup script:**

```zsh
./setup-db.sh
```

This will:
- Check if PostgreSQL is installed (offer to install if not)
- Create the database
- Generate configuration files
- Set up everything for you!

Then run: `mvn spring-boot:run`

---

## Manual Setup (If you prefer step-by-step)

## Step 1: Install PostgreSQL on macOS

### Option A: Using Homebrew (Recommended)

```zsh
# Install PostgreSQL
brew install postgresql@16

# Start PostgreSQL service
brew services start postgresql@16

# Verify installation
psql --version
```

### Option B: Using Postgres.app

1. Download from [https://postgresapp.com/](https://postgresapp.com/)
2. Drag to Applications folder
3. Open Postgres.app and click "Initialize"
4. Add to PATH in `~/.zshrc`:
   ```zsh
   echo 'export PATH="/Applications/Postgres.app/Contents/Versions/latest/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```

## Step 2: Create the HMS Database

```zsh
# On macOS with Homebrew, PostgreSQL uses your macOS username by default
# Open PostgreSQL command line
psql postgres

# Create the database
CREATE DATABASE hms_db;

# Verify the database was created
\l

# Connect to the database
\c hms_db

# Exit psql
\q
```

## Step 3: Configure Application Properties

You have **two options** for configuration:

### Option A: Using Environment Variables (Recommended for Security)

The application is already configured to use environment variables with sensible defaults.

**For Terminal (zsh):**

```zsh
# Set environment variables before running the app
export DB_URL=jdbc:postgresql://localhost:5432/hms_db
export DB_USERNAME=$(whoami)  # or your PostgreSQL username
export DB_PASSWORD=your_password_here

# Run the application
mvn spring-boot:run

# OR add to ~/.zshrc for permanent setup:
echo 'export DB_URL=jdbc:postgresql://localhost:5432/hms_db' >> ~/.zshrc
echo 'export DB_USERNAME='$(whoami) >> ~/.zshrc
echo 'export DB_PASSWORD=your_password' >> ~/.zshrc
source ~/.zshrc
```

**For IntelliJ IDEA:**

1. Go to Run > Edit Configurations
2. Select your Spring Boot configuration
3. Add Environment Variables:
   - `DB_URL=jdbc:postgresql://localhost:5432/hms_db`
   - `DB_USERNAME=postgres`
   - `DB_PASSWORD=your_password_here`

**For VS Code:**

Create `.vscode/launch.json`:
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Spring Boot HMS",
      "request": "launch",
      "mainClass": "com.hms.HmsApplication",
      "env": {
        "DB_URL": "jdbc:postgresql://localhost:5432/hms_db",
        "DB_USERNAME": "postgres",
        "DB_PASSWORD": "your_password_here"
      }
    }
  ]
}
```

### Option B: Using application-local.properties (Simpler for Local Development)

1. Copy the example file:
   ```bash
   cp src/main/resources/application-local.properties.example src/main/resources/application-local.properties
   ```

2. Edit `application-local.properties` with your credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/hms_db
   spring.datasource.username=postgres
   spring.datasource.password=your_actual_password
   ```

3. Run with the local profile:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=local
   ```

   Or add to IntelliJ Run Configuration:
   - VM options: `-Dspring.profiles.active=local`

## Step 4: Default PostgreSQL Credentials (macOS Homebrew)

If you just installed PostgreSQL using Homebrew on macOS, the defaults are:

- **Username:** Your macOS username (run `whoami` to see it)
- **Password:** Usually empty (no password by default)
- **Host:** `localhost`
- **Port:** `5432`
- **Database:** You need to create `hms_db` (see Step 2)

## Step 5: Set a Password for PostgreSQL User (Optional but Recommended)

```zsh
# Connect to PostgreSQL
psql postgres

# Set password for your user (replace with your macOS username)
ALTER USER $(whoami) WITH PASSWORD 'your_secure_password';

# Exit
\q
```

## Step 6: Test the Connection

1. Update your configuration with the correct password
2. Run the application:
   ```zsh
   mvn clean install
   mvn spring-boot:run
   ```

3. Check the logs - you should see:
   ```
   HikariPool-1 - Starting...
   HikariPool-1 - Start completed.
   ```

4. Your data will now persist across application restarts!

## Troubleshooting

### Connection Refused Error

```zsh
# Check if PostgreSQL is running
brew services list

# If not running, start it
brew services start postgresql@16

# Check PostgreSQL status
pg_isready
```

### Password Authentication Failed

```zsh
# Reset password for your user
psql postgres
ALTER USER $(whoami) WITH PASSWORD 'newpassword';
\q

# Update your .env or application-local.properties with the new password
```

### Port Already in Use

```zsh
# Check what's using port 5432
lsof -i :5432

# Or change the port in your configuration
spring.datasource.url=jdbc:postgresql://localhost:5433/hms_db
```

### "psql: command not found" on macOS

```zsh
# If using Homebrew PostgreSQL@16, add to PATH
echo 'export PATH="/opt/homebrew/opt/postgresql@16/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc

# Or for Intel Macs:
echo 'export PATH="/usr/local/opt/postgresql@16/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

## Useful PostgreSQL Commands (macOS)

```zsh
# Connect to database (using your macOS username)
psql -d hms_db

# Or with specific user
psql -U $(whoami) -d hms_db

# List all databases
\l

# Connect to a database
\c hms_db

# List all tables
\dt

# Describe a table
\d patients

# View all data in a table
SELECT * FROM patients;

# Show current user
SELECT current_user;

# Exit
\q
```

## Quick Commands for macOS

```zsh
# Start PostgreSQL
brew services start postgresql@16

# Stop PostgreSQL
brew services stop postgresql@16

# Restart PostgreSQL
brew services restart postgresql@16

# Check PostgreSQL status
brew services list | grep postgresql
```

## Git Safety

The following files are already in `.gitignore`:
- `.env`
- `application-local.properties`
- `application-prod.properties`

**Never commit these files!** They contain sensitive credentials.

Always use:
- `.env.example` for environment variable templates
- `application-local.properties.example` for configuration templates

## What's Changed

1. ✅ **pom.xml** - Added PostgreSQL driver dependency
2. ✅ **application.properties** - Configured for PostgreSQL with environment variables
3. ✅ **.gitignore** - Already configured to ignore sensitive files
4. ✅ **Database** - Data now persists across restarts

## Next Steps

1. Install PostgreSQL
2. Create the `hms_db` database
3. Set your credentials using one of the methods above
4. Run the application
5. Your data will now be saved permanently!
