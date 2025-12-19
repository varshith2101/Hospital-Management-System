# Hospital Management System - Deployment Guide

Complete guide for deploying the HMS backend and frontend to production.

## Prerequisites

- Git installed
- GitHub account
- Code pushed to GitHub repository

---

## Part 1: Backend Deployment (Railway)

Railway provides free PostgreSQL database and hosting for Spring Boot applications.

### Step 1: Create Railway Account

1. Go to [railway.app](https://railway.app)
2. Click "Login" → Sign in with GitHub
3. Authorize Railway to access your GitHub

### Step 2: Create New Project

1. Click "New Project"
2. Select "Deploy from GitHub repo"
3. Select your HMS repository
4. Railway will detect it as a Java/Maven project

### Step 3: Add PostgreSQL Database

1. In your project, click "New"
2. Select "Database" → "Add PostgreSQL"
3. Railway will provision a PostgreSQL database
4. Click on the PostgreSQL service to see connection details

### Step 4: Configure Environment Variables

1. Click on your backend service (not the database)
2. Go to "Variables" tab
3. Add these environment variables:

```
DB_URL=postgresql://<hostname>:<port>/<database>
DB_USERNAME=<username>
DB_PASSWORD=<password>
ALLOWED_ORIGINS=http://localhost:3000
LOG_LEVEL=INFO
SQL_LOG_LEVEL=WARN
```

**Important:** Get the database credentials from your PostgreSQL service:
- Click on PostgreSQL service
- Go to "Connect" tab
- Copy the connection details

**Easier Method:** Railway provides a connection string. Parse it:
```
postgresql://user:password@hostname:port/database
```

Then set:
```
DB_URL=jdbc:postgresql://hostname:port/database
DB_USERNAME=user
DB_PASSWORD=password
```

### Step 5: Configure Build & Deploy

1. Go to "Settings" tab in your backend service
2. Under "Build Command", ensure it's: `mvn clean install -DskipTests`
3. Under "Start Command", ensure it's: `java -jar target/*.jar`
4. Set "Root Directory" to `/` (since backend is in root)

### Step 6: Deploy

1. Click "Deploy" or push changes to GitHub
2. Railway will automatically build and deploy
3. Check "Deployments" tab for build logs
4. Once deployed, click "Settings" → "Generate Domain" to get your backend URL

### Step 7: Test Backend

Your backend will be available at: `https://your-app.railway.app`

Test the health endpoint:
```
https://your-app.railway.app/actuator/health
```

Test an API endpoint:
```
https://your-app.railway.app/api/doctors
```

### Step 8: Update ALLOWED_ORIGINS (After Frontend Deployment)

Once you deploy the frontend, update the `ALLOWED_ORIGINS` variable:
```
ALLOWED_ORIGINS=https://your-frontend.vercel.app,http://localhost:3000
```

---

## Part 2: Frontend Deployment (Vercel)

Vercel is perfect for React/Vite applications with automatic deployments.

### Step 1: Prepare Frontend Repository

**Option A: Separate Repository (Recommended)**
1. Create a new GitHub repository for frontend
2. Copy the `frontend/` directory contents to the new repo
3. Push to GitHub

**Option B: Same Repository**
1. Keep frontend in the same repo
2. Configure Vercel to use `frontend/` as root directory

### Step 2: Create Vercel Account

1. Go to [vercel.com](https://vercel.com)
2. Click "Sign Up" → Sign in with GitHub
3. Authorize Vercel

### Step 3: Import Project

1. Click "Add New..." → "Project"
2. Import your frontend repository
3. Configure project:
   - **Framework Preset:** Vite
   - **Root Directory:** `frontend/` (if using same repo) or `./` (if separate repo)
   - **Build Command:** `npm run build`
   - **Output Directory:** `dist`

### Step 4: Set Environment Variables

Before deploying, add environment variable:

1. Click "Environment Variables"
2. Add:
   ```
   VITE_API_URL=https://your-backend.railway.app/api
   ```
   (Use the Railway URL from Part 1, Step 6)

### Step 5: Deploy

1. Click "Deploy"
2. Vercel will build and deploy your frontend
3. You'll get a URL like: `https://your-app.vercel.app`

### Step 6: Update Backend CORS

Go back to Railway and update `ALLOWED_ORIGINS`:
```
ALLOWED_ORIGINS=https://your-app.vercel.app,http://localhost:3000
```

### Step 7: Test Complete Application

1. Visit your Vercel URL: `https://your-app.vercel.app`
2. Test all features:
   - View dashboard
   - Create/edit patients
   - Create/edit doctors
   - Schedule appointments
   - Manage beds

---

## Alternative Deployment Options

### Backend Alternatives

#### Option 1: Render
- Go to [render.com](https://render.com)
- Create "New Web Service"
- Connect GitHub repo
- Environment: `Docker` or `Java`
- Add PostgreSQL from "New" → "PostgreSQL"
- Set environment variables
- Deploy

#### Option 2: Heroku
- Install Heroku CLI
- Create app: `heroku create your-app-name`
- Add PostgreSQL: `heroku addons:create heroku-postgresql:mini`
- Set config vars: `heroku config:set ALLOWED_ORIGINS=...`
- Deploy: `git push heroku main`

### Frontend Alternatives

#### Option 1: Netlify
1. Go to [netlify.com](https://netlify.com)
2. "Add new site" → "Import from Git"
3. Select repository
4. Build settings:
   - Build command: `npm run build`
   - Publish directory: `dist`
   - Base directory: `frontend/` (if needed)
5. Add environment variable: `VITE_API_URL`
6. Deploy

#### Option 2: Railway (Frontend)
1. Add new service to your Railway project
2. Select "Deploy from GitHub repo"
3. Choose frontend (or use nixpacks detection)
4. Set environment variable: `VITE_API_URL`
5. Deploy

---

## Environment Variables Reference

### Backend (Railway/Render/Heroku)

| Variable | Example | Required |
|----------|---------|----------|
| `DB_URL` | `jdbc:postgresql://host:5432/db` | Yes |
| `DB_USERNAME` | `postgres` | Yes |
| `DB_PASSWORD` | `your-password` | Yes |
| `ALLOWED_ORIGINS` | `https://app.vercel.app` | Yes |
| `LOG_LEVEL` | `INFO` | No (default: DEBUG) |
| `SQL_LOG_LEVEL` | `WARN` | No (default: DEBUG) |

### Frontend (Vercel/Netlify)

| Variable | Example | Required |
|----------|---------|----------|
| `VITE_API_URL` | `https://backend.railway.app/api` | Yes |

---

## Troubleshooting

### Backend Issues

**Build fails:**
- Check Java version is 17+
- Verify `pom.xml` is valid
- Check Railway build logs

**Database connection fails:**
- Verify `DB_URL` format: `jdbc:postgresql://...`
- Check database credentials
- Ensure PostgreSQL service is running

**CORS errors:**
- Add frontend URL to `ALLOWED_ORIGINS`
- Include `http://` or `https://`
- Separate multiple origins with commas

### Frontend Issues

**API calls fail:**
- Check `VITE_API_URL` is set correctly
- Verify backend URL is accessible
- Check browser console for CORS errors

**Build fails:**
- Clear node_modules: `rm -rf node_modules && npm install`
- Check Node version: `node -v` (should be 16+)

**Environment variable not working:**
- Ensure it starts with `VITE_`
- Rebuild after adding variables
- Check Vercel/Netlify deployment logs

---

## Post-Deployment Checklist

- [ ] Backend health check passes: `/actuator/health`
- [ ] Database seeded with test data
- [ ] Frontend loads successfully
- [ ] Can view dashboard statistics
- [ ] Can create/edit patients
- [ ] Can create/edit doctors
- [ ] Can schedule appointments
- [ ] Can manage bed availability
- [ ] CORS configured correctly
- [ ] Environment variables secured
- [ ] Custom domain configured (optional)

---

## Cost Estimate

### Free Tier (Recommended for Development)

- **Railway:** $5/month free credit (enough for small apps)
- **Vercel:** Free for personal projects
- **Total:** ~$0-5/month

### Production Scale

- **Railway:** ~$10-20/month (with usage)
- **Render:** $7/month (PostgreSQL) + $7/month (Web Service)
- **Vercel Pro:** $20/month (for teams)

---

## Security Best Practices

1. **Never commit `.env` files** - Use `.env.example` as template
2. **Use environment variables** for all sensitive data
3. **Enable HTTPS** - Vercel/Railway provide this automatically
4. **Rotate credentials** regularly
5. **Limit CORS origins** to specific domains
6. **Use production profile** in backend (`spring.profiles.active=prod`)
7. **Disable SQL logging** in production
8. **Set up monitoring** using Railway/Vercel dashboards

---

## Support

For issues:
1. Check deployment logs in Railway/Vercel
2. Review this guide
3. Check backend health endpoint
4. Verify environment variables
5. Test API endpoints directly

---

**Deployment completed!** Your Hospital Management System is now live.
