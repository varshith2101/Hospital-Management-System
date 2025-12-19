# 100% FREE Deployment Guide

Deploy your Hospital Management System completely free using:
- **Render** (Backend hosting - FREE)
- **Neon** (PostgreSQL database - FREE forever)
- **Vercel** (Frontend hosting - FREE forever)

**Performance Trade-offs:**
- Backend spins down after 15 minutes of inactivity (first request takes ~30-60 seconds to wake up)
- Database has connection limits (but sufficient for development/portfolio)
- No credit card required for any service!

---

## Part 1: Database Setup (Neon) - FREE Forever

Neon offers a generous free PostgreSQL database with no credit card required.

### Step 1: Create Neon Account

1. Go to **[neon.tech](https://neon.tech)**
2. Click **"Sign up"** → Sign in with **GitHub**
3. Authorize Neon

### Step 2: Create Database

1. Click **"Create a project"**
2. Project name: `hms-database` (or your choice)
3. PostgreSQL version: **15** or **16**
4. Region: Choose closest to you
5. Click **"Create project"**

### Step 3: Get Connection String

1. After creation, you'll see **"Connection Details"**
2. Copy the connection string that looks like:
```
postgresql://user:password@ep-xxx-xxx.region.aws.neon.tech/neondb?sslmode=require
```

3. **Save this!** You'll need it for backend deployment.

**Format it for Spring Boot:**
```
Original: postgresql://user:password@host:5432/database?sslmode=require

You need:
DB_URL=jdbc:postgresql://host:5432/database?sslmode=require
DB_USERNAME=user
DB_PASSWORD=password
```

### Step 4: Keep Database Alive (Optional)

Neon free tier suspends after 5 minutes of inactivity. To keep it active:
- Your app will automatically wake it on first request
- Or use [cron-job.org](https://cron-job.org) to ping your health endpoint every 14 minutes

✅ **Database ready!**

**Free Tier Limits:**
- 0.5 GB storage (plenty for development)
- Shared compute
- Suspends after 5 min inactivity
- No credit card needed
- Forever free

---

## Part 2: Backend Deployment (Render) - FREE

Render offers 750 free hours per month (enough for one always-on app).

### Step 1: Create Render Account

1. Go to **[render.com](https://render.com)**
2. Click **"Get Started"** → **"Sign in with GitHub"**
3. Authorize Render

### Step 2: Deploy Backend

1. Click **"New +"** → **"Web Service"**
2. Click **"Build and deploy from a Git repository"** → **"Next"**
3. Connect your GitHub repository
4. If not listed, click **"Configure account"** → Select your repo

### Step 3: Configure Web Service

Fill in these settings:

| Field | Value |
|-------|-------|
| **Name** | `hms-backend` (or your choice) |
| **Region** | Choose closest to you |
| **Branch** | `main` |
| **Root Directory** | Leave blank (or `/` if backend is in root) |
| **Runtime** | `Java` |
| **Build Command** | `mvn clean install -DskipTests` |
| **Start Command** | `java -jar target/*.jar` |
| **Instance Type** | **Free** |

### Step 4: Add Environment Variables

Scroll down to **"Environment Variables"** and add:

```bash
DB_URL=jdbc:postgresql://ep-xxx-xxx.region.aws.neon.tech/neondb?sslmode=require
DB_USERNAME=your_neon_username
DB_PASSWORD=your_neon_password
ALLOWED_ORIGINS=http://localhost:3000
LOG_LEVEL=INFO
SQL_LOG_LEVEL=WARN
```

**Use your Neon connection details from Part 1, Step 3!**

### Step 5: Create Web Service

1. Click **"Create Web Service"**
2. Render will start building (takes 5-10 minutes first time)
3. Watch the logs in real-time

### Step 6: Get Backend URL

1. Once deployed, you'll see a green **"Live"** badge
2. Your URL will be: `https://hms-backend.onrender.com` (or similar)
3. **Save this URL!** You'll need it for frontend.

### Step 7: Test Backend

Visit: `https://hms-backend.onrender.com/actuator/health`

Should see: `{"status":"UP"}`

**Note:** First request takes 30-60 seconds if app was sleeping!

✅ **Backend deployed!**

**Free Tier Limits:**
- 750 hours/month per account (enough for 1 app always-on)
- Spins down after 15 min inactivity
- Takes 30-60 seconds to wake up
- 512 MB RAM
- Shared CPU
- No credit card needed

---

## Part 3: Frontend Deployment (Vercel) - FREE Forever

Vercel is perfect for React apps and 100% free for personal projects.

### Step 1: Create Vercel Account

1. Go to **[vercel.com](https://vercel.com)**
2. Click **"Sign Up"** → **"Continue with GitHub"**
3. Authorize Vercel

### Step 2: Import Project

1. Click **"Add New..."** → **"Project"**
2. Find your repository
3. Click **"Import"**

### Step 3: Configure Project

| Field | Value |
|-------|-------|
| **Framework Preset** | Vite (auto-detected) |
| **Root Directory** | `frontend/` |
| **Build Command** | `npm run build` |
| **Output Directory** | `dist` |

### Step 4: Add Environment Variable

**BEFORE deploying**, expand **"Environment Variables"**:

```bash
VITE_API_URL=https://hms-backend.onrender.com/api
```

**Use YOUR Render URL from Part 2, Step 6!**

### Step 5: Deploy

1. Click **"Deploy"**
2. Wait 2-3 minutes
3. Your app will be live at: `https://your-app.vercel.app`

✅ **Frontend deployed!**

**Free Tier Limits:**
- Unlimited websites
- 100 GB bandwidth/month
- Automatic HTTPS
- Global CDN
- Forever free for personal projects

---

## Part 4: Connect Everything (5 minutes)

### Update CORS Settings

1. Go back to **Render**
2. Click on your backend service
3. Go to **"Environment"** in left sidebar
4. Update `ALLOWED_ORIGINS`:

```bash
ALLOWED_ORIGINS=https://your-app.vercel.app,http://localhost:3000
```

**Use YOUR Vercel URL from Part 3, Step 5!**

5. Click **"Save Changes"**
6. Render will redeploy automatically

### Test Complete Application

1. Visit your Vercel URL: `https://your-app.vercel.app`
2. **Wait 30-60 seconds** for first load (backend waking up)
3. Dashboard should load with seeded data
4. Test all features:
   - ✅ View dashboard
   - ✅ Create patient
   - ✅ Create doctor
   - ✅ Schedule appointment
   - ✅ Manage beds

✅ **Everything works!**

---

## Your Live URLs

Save these:

- **Frontend:** `https://your-app.vercel.app`
- **Backend:** `https://hms-backend.onrender.com`
- **Database:** Neon dashboard (for monitoring)

---

## Alternative FREE Options

### Option 1: Render + Supabase + Vercel

**Replace Neon with Supabase:**
- Go to [supabase.com](https://supabase.com)
- Create free PostgreSQL database
- Use connection details in Render

**Pros:** Better dashboard, more features
**Cons:** Same connection limits

### Option 2: Fly.io + Neon + Vercel

**Replace Render with Fly.io:**
- Go to [fly.io](https://fly.io)
- Deploy with `flyctl launch`
- 3 shared VMs free
- Less sleep time

**Pros:** Better performance, less sleep
**Cons:** Requires CLI setup

### Option 3: Koyeb + Neon + Vercel

**Replace Render with Koyeb:**
- Go to [koyeb.com](https://koyeb.com)
- Deploy from GitHub
- Free tier available

**Pros:** Similar to Render
**Cons:** Smaller community

---

## Performance Optimization Tips

### 1. Keep Backend Alive

Use a free cron service to ping your backend every 14 minutes:

1. Go to [cron-job.org](https://cron-job.org)
2. Create free account
3. Add job: `https://hms-backend.onrender.com/actuator/health`
4. Schedule: Every 14 minutes
5. Backend stays awake!

### 2. Optimize First Load

Add loading message in frontend for initial wake-up:
- Users know to wait 30-60 seconds
- Better UX than timing out

### 3. Database Connection Pooling

Already configured in `application.properties`:
- HikariCP automatically handles connections
- Works well with Neon free tier

---

## Troubleshooting

### "Backend takes forever to load"

**Reason:** Render free tier spins down after 15 min inactivity.

**Fix:**
- Wait 30-60 seconds for first request
- Set up cron job to keep alive (see above)
- Upgrade to Render paid ($7/month) for always-on

### "Database connection failed"

**Check:**
1. Neon database is active (check Neon dashboard)
2. Connection string is correct
3. SSL mode is included: `?sslmode=require`

**Fix:**
- Verify `DB_URL` in Render environment variables
- Check Neon dashboard for connection string
- Ensure format: `jdbc:postgresql://...?sslmode=require`

### "CORS error in browser"

**Check:**
1. `ALLOWED_ORIGINS` includes your Vercel URL
2. No typos in URL
3. Includes `https://` prefix

**Fix:**
- Update `ALLOWED_ORIGINS` in Render
- Redeploy backend
- Clear browser cache

### "502 Bad Gateway"

**Reason:** Backend is starting up.

**Fix:**
- Wait 60 seconds and refresh
- Check Render logs for errors

---

## Monitoring & Logs

### Render Logs

1. Go to Render dashboard
2. Click your service
3. Click **"Logs"** tab
4. See real-time logs

### Neon Monitoring

1. Go to Neon dashboard
2. Click your project
3. View **"Monitoring"** tab
4. See queries, connections, storage

### Vercel Analytics

1. Go to Vercel dashboard
2. Click your project
3. Click **"Analytics"** tab
4. See visitor stats (free basic analytics)

---

## Upgrade Path (Future)

When you need better performance:

| Service | Free | Paid | Cost |
|---------|------|------|------|
| **Render** | 750h, sleeps | Always-on | $7/month |
| **Neon** | 0.5GB, sleeps | 3GB, less sleep | $19/month |
| **Vercel** | Free forever | Pro features | $20/month |

**Recommended first upgrade:** Render paid tier ($7/month) for always-on backend.

---

## Cost Summary

**Current:** $0/month (100% FREE!)

**With optimizations:**
- Cron job to keep alive: FREE
- Custom domain: $12/year (optional)

**Total:** $0/month forever (or $1/month for domain)

---

## Deployment Checklist

- [ ] Neon database created
- [ ] Neon connection string copied
- [ ] Render account created
- [ ] Backend deployed on Render
- [ ] Environment variables set in Render
- [ ] Backend health check passes
- [ ] Vercel account created
- [ ] Frontend deployed on Vercel
- [ ] VITE_API_URL set in Vercel
- [ ] ALLOWED_ORIGINS updated in Render
- [ ] Frontend loads successfully
- [ ] All features tested
- [ ] (Optional) Cron job set up to keep backend alive

---

## Next Steps

1. **Test thoroughly** - Try all features
2. **Set up cron job** - Keep backend alive
3. **Share with friends** - Get feedback
4. **Monitor usage** - Check Neon/Render dashboards
5. **Add to portfolio** - Include live URL in resume!

---

**Congratulations!** You've deployed your Hospital Management System for **FREE**! 🎉

**No credit cards. No trials. 100% Free forever.**
