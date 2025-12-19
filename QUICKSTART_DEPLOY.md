# Quick Start - Deploy HMS in 15 Minutes

This guide gets your Hospital Management System live on the internet quickly.

## What You'll Need

- GitHub account (free)
- 15 minutes of time
- Your HMS code pushed to GitHub

---

## Step 1: Deploy Backend (5 minutes)

### A. Create Railway Account

1. Go to **[railway.app](https://railway.app)**
2. Click **"Login with GitHub"**
3. Authorize Railway

### B. Deploy Backend

1. Click **"New Project"**
2. Select **"Deploy from GitHub repo"**
3. Choose your HMS repository
4. Railway auto-detects Java/Maven ✅

### C. Add Database

1. Click **"New"** → **"Database"** → **"Add PostgreSQL"**
2. PostgreSQL database created ✅

### D. Configure Environment Variables

1. Click on your **backend service** (not the database)
2. Go to **"Variables"** tab
3. Click on **PostgreSQL** service → **"Variables"** → Copy connection string
4. Back to backend service **"Variables"**, add:

**Easy Method** - If Railway shows `DATABASE_URL`:
```bash
# Click "New Variable" → "Add Reference" → Select PostgreSQL → DATABASE_URL
# Then manually add these:

DB_URL=jdbc:postgresql://[copy from DATABASE_URL but change format]
DB_USERNAME=[from DATABASE_URL]
DB_PASSWORD=[from DATABASE_URL]
ALLOWED_ORIGINS=http://localhost:3000
```

**Easier Method** - Railway provides individual variables:
```bash
DB_URL=jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}
DB_USERNAME=${{Postgres.PGUSER}}
DB_PASSWORD=${{Postgres.PGPASSWORD}}
ALLOWED_ORIGINS=http://localhost:3000
```

### E. Get Backend URL

1. Go to **"Settings"** tab
2. Click **"Generate Domain"**
3. Copy your URL: `https://your-app.railway.app`
4. **Save this URL!** You'll need it for frontend.

### F. Test Backend

Visit: `https://your-app.railway.app/actuator/health`

Should see: `{"status":"UP"}`

✅ **Backend deployed!**

---

## Step 2: Deploy Frontend (5 minutes)

### A. Create Vercel Account

1. Go to **[vercel.com](https://vercel.com)**
2. Click **"Sign Up with GitHub"**
3. Authorize Vercel

### B. Deploy Frontend

1. Click **"Add New..."** → **"Project"**
2. Click **"Import"** on your repository
3. Configure:
   - **Root Directory:** `frontend/` (or leave blank if frontend is in separate repo)
   - **Framework Preset:** Vite (auto-detected)
   - **Build Command:** `npm run build`
   - **Output Directory:** `dist`

### C. Add Environment Variable

**BEFORE deploying**, click **"Environment Variables"**:

```bash
VITE_API_URL=https://your-app.railway.app/api
```

Replace `your-app.railway.app` with YOUR Railway URL from Step 1E!

### D. Deploy

1. Click **"Deploy"**
2. Wait 2-3 minutes
3. Get your URL: `https://your-app.vercel.app`

✅ **Frontend deployed!**

---

## Step 3: Connect Frontend & Backend (2 minutes)

### Update CORS

1. Go back to **Railway**
2. Click on **backend service**
3. Go to **"Variables"**
4. Update `ALLOWED_ORIGINS`:

```bash
ALLOWED_ORIGINS=https://your-app.vercel.app,http://localhost:3000
```

Replace `your-app.vercel.app` with YOUR Vercel URL!

5. Click **"Save"**
6. Railway will redeploy automatically

---

## Step 4: Test Everything (3 minutes)

1. Visit your Vercel URL: `https://your-app.vercel.app`
2. Dashboard should load with stats
3. Try creating a patient
4. Try creating a doctor
5. Try scheduling an appointment

✅ **Everything works!**

---

## Your URLs

Save these:

- **Frontend:** `https://your-app.vercel.app`
- **Backend:** `https://your-app.railway.app`
- **Backend API:** `https://your-app.railway.app/api`
- **Backend Health:** `https://your-app.railway.app/actuator/health`

---

## Troubleshooting

### "Cannot connect to backend"

**Check:**
1. Is `VITE_API_URL` set correctly in Vercel?
2. Does it end with `/api`?
3. Did you update `ALLOWED_ORIGINS` in Railway?

**Fix:**
- Vercel: Settings → Environment Variables → `VITE_API_URL`
- Railway: Backend → Variables → `ALLOWED_ORIGINS`

### "Database connection failed"

**Check:**
1. Is PostgreSQL service running in Railway?
2. Are database variables set correctly?

**Fix:**
- Railway: PostgreSQL service → Check status
- Backend service → Variables → Verify DB_URL, DB_USERNAME, DB_PASSWORD

### "404 Not Found"

**Check:**
1. Did backend deploy successfully?
2. Check Railway deployment logs

**Fix:**
- Railway: Backend → Deployments → View logs
- Look for errors in build/deploy

---

## Next Steps

1. ✅ Add your custom domain (optional)
   - Vercel: Settings → Domains
   - Railway: Settings → Domains

2. ✅ Invite team members
   - Vercel: Settings → Team
   - Railway: Settings → Team

3. ✅ Set up monitoring
   - Railway: Built-in metrics
   - Vercel: Analytics tab

4. ✅ Configure auto-deploy
   - Both platforms auto-deploy on `git push` to main branch

---

## Cost

- **Railway:** $5/month free credits (enough for development)
- **Vercel:** Free forever for personal projects
- **Total:** $0-5/month

---

## Support

**Stuck?** Check:
1. Railway deployment logs
2. Vercel deployment logs
3. Browser console (F12)
4. Backend health endpoint

**Common fixes:**
- Clear browser cache
- Redeploy both services
- Check environment variables
- Verify CORS settings

---

**Congratulations!** Your Hospital Management System is live! 🎉
