# 🏥 Healthcare Management System

> A cloud-native Hospital Management System built with Spring Boot, featuring real-time bed availability tracking and comprehensive REST APIs for healthcare operations.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![React](https://img.shields.io/badge/React-18-61dafb.svg)](https://reactjs.org/)
[![Live](https://img.shields.io/badge/Status-Live-success.svg)](https://your-hms-frontend.vercel.app)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 🌐 Live Demo

**⚡ The application is LIVE and deployed!**

- **Frontend (React):** [https://your-hms-frontend.vercel.app](https://your-hms-frontend.vercel.app) 🎨
- **Backend API:** [Live on Render](https://your-hms-backend.onrender.com) ⚙️
- **Database:** PostgreSQL on Neon (Serverless) 🗄️

> **Note:** Backend may take 30-60 seconds to wake up on first request (free tier limitation)

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Real-Time Features](#-real-time-features)
- [Deployment](#-deployment)
- [Project Structure](#-project-structure)
- [Documentation](#-documentation)
- [Contributing](#-contributing)

---

## 🎯 Overview

The Healthcare Management System (HMS) is a modern, cloud-native full-stack application designed to streamline hospital operations. Built with Spring Boot backend and React frontend, following microservices principles, it provides comprehensive management capabilities for doctors, patients, appointments, and real-time bed availability tracking.

### What Makes This Special?

- **Real-Time Bed Allocation** - Transaction-based bed management system similar to ATM operations in banking systems
- **Neo-Brutalism Design** - Bold, modern UI with vibrant colors and geometric shapes
- **Cloud-Native** - Fully deployed on cloud infrastructure (Render + Neon + Vercel)
- **Production-Ready** - Health checks, metrics, monitoring, and live deployment
- **100% Free Hosting** - Deployed without any hosting costs using free tiers

---

## ✨ Key Features

### Core Functionality
- 🏥 **Doctor Management** - Complete CRUD operations with availability tracking
- 👥 **Patient Management** - Patient registration, admission, and discharge workflows
- 📅 **Appointment Booking** - Schedule and manage doctor-patient appointments
- 🛏️ **Real-Time Bed Availability** - Ward-wise bed tracking with instant allocation/release
- 📊 **Medical History** - Comprehensive patient medical record management
- 🎨 **Neo-Brutalism UI** - Bold, modern design with vibrant colors

### Technical Highlights
- ✅ **31+ REST API Endpoints** - Comprehensive API coverage
- ✅ **Real-Time Transaction Validation** - Prevents double-booking and race conditions
- ✅ **PostgreSQL Database** - Production database on Neon serverless
- ✅ **Auto-Seeded Data** - 15 doctors, 25 patients, 50 appointments, 53 beds
- ✅ **CORS Configured** - Secure frontend-backend communication
- ✅ **Health Monitoring** - Spring Boot Actuator with liveness/readiness probes
- ✅ **Live Deployment** - Fully deployed and accessible on the internet

---

## 🛠️ Technology Stack

### Backend
- **Java 17** - LTS version with modern features
- **Spring Boot 3.2.0** - Enterprise-grade framework
- **Spring Data JPA** - Object-Relational Mapping
- **Hibernate** - ORM implementation
- **Maven** - Dependency management and build automation
- **PostgreSQL** - Production database (Neon serverless)

### Frontend
- **React 18** - Modern UI library
- **Vite** - Next-generation build tool
- **TailwindCSS** - Utility-first CSS with neo-brutalism design
- **Axios** - HTTP client
- **React Router** - Client-side routing

### Deployment & Cloud
- **Render** - Backend hosting (FREE tier)
- **Neon** - Serverless PostgreSQL (FREE tier)
- **Vercel** - Frontend hosting (FREE tier)
- **Spring Boot Actuator** - Application monitoring and metrics

### Architecture Patterns
- **Microservices Architecture** - Domain-driven service separation
- **Layered Architecture** - Controller → Service → Repository → Entity
- **Repository Pattern** - Data access abstraction
- **RESTful Design** - Standard HTTP methods and status codes

---

## 🏗️ Architecture

### System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     Client Applications                      │
│              (Web, Mobile, Desktop, APIs)                   │
└────────────────────┬────────────────────────────────────────┘
                     │ HTTP/REST
┌────────────────────▼────────────────────────────────────────┐
│                   Load Balancer (K8s Service)                │
└────────────────────┬────────────────────────────────────────┘
                     │
        ┌────────────┼────────────┐
        │            │            │
┌───────▼──────┐ ┌──▼──────┐ ┌──▼──────┐
│   Pod 1      │ │  Pod 2  │ │  Pod 3  │  (Auto-scaled)
│ HMS Instance │ │HMS Inst.│ │HMS Inst.│
└───────┬──────┘ └──┬──────┘ └──┬──────┘
        │           │           │
        └───────────┼───────────┘
                    │
        ┌───────────▼────────────┐
        │   Database (MySQL/H2)   │
        └────────────────────────┘
```

### Application Layers

```
┌─────────────────────────────────────┐
│    Controller Layer                 │  ← REST API Endpoints
│    (HTTP Request/Response)          │     (@RestController)
└─────────────────┬───────────────────┘
                  │
┌─────────────────▼───────────────────┐
│    Service Layer                    │  ← Business Logic
│    (Validation, Processing)         │     (@Service)
└─────────────────┬───────────────────┘
                  │
┌─────────────────▼───────────────────┐
│    Repository Layer                 │  ← Data Access
│    (Database Operations)            │     (JpaRepository)
└─────────────────┬───────────────────┘
                  │
┌─────────────────▼───────────────────┐
│    Entity Layer                     │  ← Data Models
│    (Database Tables)                │     (@Entity)
└─────────────────────────────────────┘
```

### Database Schema

```
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│  doctors    │◄──────┤ appointments │──────►│  patients   │
├─────────────┤       ├──────────────┤       ├─────────────┤
│ id (PK)     │       │ id (PK)      │       │ id (PK)     │
│ name        │       │ doctor_id FK │       │ name        │
│ age         │       │ patient_id FK│       │ age         │
│ special...  │       │ appt_date    │       │ phone       │
│ is_available│       │ reason       │       │ is_admitted │
└─────────────┘       │ status       │       └──────┬──────┘
                      └──────────────┘              │
                                                    │ 1:1
                                          ┌─────────▼──────────┐
                                          │ medical_history    │
                                          ├────────────────────┤
                                          │ id (PK)            │
                                          │ has_diabetes       │
                                          │ medications        │
                                          │ allergies          │
                                          └────────────────────┘
┌──────────────────┐
│ bed_availability │
├──────────────────┤
│ id (PK)          │
│ bed_number       │
│ ward_name        │
│ is_available     │
│ patient_id (FK)  │─────► patients.id
└──────────────────┘
```

---

## 🚀 Getting Started

### Try the Live Application

The easiest way to try HMS is to visit the live deployment:

🌐 **[Live Demo](https://your-hms-frontend.vercel.app)**

The application is pre-loaded with sample data including:
- 15 Doctors across various specializations
- 25 Patients with medical histories
- 50 Appointments (past, present, and future)
- 53 Beds across ICU, General, VIP, and Emergency wards

---

### Local Development

#### Prerequisites

- **Java 17** or higher
- **Maven 3.9+**
- **Node.js 16+** (for frontend)
- **PostgreSQL** (or use local setup script)

#### Backend Setup

1. Clone the repository
```bash
git clone https://github.com/Varshith2101/hms.git
cd hms
```

2. Set up PostgreSQL database (macOS)
```bash
chmod +x setup-db.sh
./setup-db.sh
```

3. Run the backend
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Backend will start on **http://localhost:8080**

#### Frontend Setup

The frontend is in a separate repository:

**Frontend Repository:** [https://github.com/yourusername/hms-frontend](https://github.com/yourusername/hms-frontend)

See the frontend README for setup instructions.

#### Verify Backend is Running

```bash
curl http://localhost:8080/actuator/health
```

Expected response:
```json
{
  "status": "UP"
}
```

---

## 📡 API Documentation

### Base URLs

**Production (Live):**
```
https://your-hms-backend.onrender.com/api
```

**Local Development:**
```
http://localhost:8080/api
```

> **Note:** First request to production may take 30-60 seconds as the server wakes up from sleep (free tier limitation)

### Doctor Management APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/doctors` | Create a new doctor |
| `GET` | `/doctors` | Get all doctors |
| `GET` | `/doctors/{id}` | Get doctor by ID |
| `GET` | `/doctors/available` | Get all available doctors |
| `GET` | `/doctors/specialization/{name}` | Filter doctors by specialization |
| `PUT` | `/doctors/{id}` | Update doctor details |
| `PATCH` | `/doctors/{id}/toggle-availability` | Toggle doctor availability |
| `DELETE` | `/doctors/{id}` | Delete a doctor |

### Patient Management APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/patients` | Register a new patient |
| `GET` | `/patients` | Get all patients |
| `GET` | `/patients/{id}` | Get patient by ID |
| `GET` | `/patients/admitted` | Get all admitted patients |
| `PUT` | `/patients/{id}` | Update patient details |
| `PATCH` | `/patients/{id}/admit` | Admit patient to hospital |
| `PATCH` | `/patients/{id}/discharge` | Discharge patient |
| `DELETE` | `/patients/{id}` | Delete a patient |

### Appointment Management APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/appointments` | Book a new appointment |
| `GET` | `/appointments` | Get all appointments |
| `GET` | `/appointments/{id}` | Get appointment by ID |
| `GET` | `/appointments/patient/{id}` | Get patient's appointments |
| `GET` | `/appointments/doctor/{id}` | Get doctor's appointments |
| `PATCH` | `/appointments/{id}/status` | Update appointment status |
| `DELETE` | `/appointments/{id}` | Cancel appointment |

### Bed Availability APIs (Real-Time)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/beds` | Add a new bed |
| `GET` | `/beds` | Get all beds |
| `GET` | `/beds/available` | Get available beds (real-time) |
| `GET` | `/beds/available/ward/{name}` | Get available beds by ward |
| `GET` | `/beds/stats` | Get real-time bed statistics |
| `GET` | `/beds/stats/ward/{name}` | Get ward-specific statistics |
| `POST` | `/beds/allocate` | Allocate bed to patient |
| `POST` | `/beds/release` | Release bed |

### Example API Requests

#### Create a Doctor
```bash
curl -X POST http://localhost:8080/api/doctors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dr. Rajesh Kumar",
    "age": 45,
    "specialization": "Cardiology",
    "qualification": "MD, DM Cardiology",
    "shiftType": "Day",
    "opdDays": "Mon,Wed,Fri",
    "dateOfJoining": "2020-01-15",
    "salary": 150000.00,
    "isAvailable": true
  }'
```

#### Check Bed Availability (Real-Time)
```bash
curl http://localhost:8080/api/beds/stats
```

Response:
```json
{
  "totalBeds": 100,
  "availableBeds": 45,
  "occupiedBeds": 55
}
```

#### Allocate Bed
```bash
curl -X POST "http://localhost:8080/api/beds/allocate?bedNumber=ICU-101&patientId=1"
```

---

## ⚡ Real-Time Features

### Real-Time Bed Availability System

The bed allocation system works similar to ATM transactions in banking:

#### ATM Withdrawal vs Bed Allocation

| ATM Transaction | Bed Allocation |
|----------------|----------------|
| Check account balance | Check bed availability |
| Verify sufficient funds | Verify bed is free |
| Withdraw cash | Allocate bed to patient |
| Update balance immediately | Update bed status & patient status |
| Prevent overdraft | Prevent double-booking |

#### How It Works

1. **Check Availability**
   ```bash
   GET /api/beds/stats
   ```
   Returns real-time count of available/occupied beds

2. **Allocate Bed**
   ```bash
   POST /api/beds/allocate?bedNumber=ICU-101&patientId=5
   ```
   - Validates bed exists and is available
   - Updates bed status to occupied
   - Updates patient admission status
   - Both operations are atomic (transaction-based)

3. **Real-Time Prevention**
   - If same bed is requested simultaneously, only one request succeeds
   - Database-level locking prevents race conditions
   - Immediate error response for conflicting requests

4. **Release Bed**
   ```bash
   POST /api/beds/release?bedNumber=ICU-101
   ```
   - Marks bed as available
   - Updates patient discharge status
   - Stats update immediately

---

## 🐳 Deployment


### Docker Deployment

#### Build Docker Image
```bash
docker build -t hms:latest .
```

#### Run Container
```bash
docker run -p 8080:8080 hms:latest
```

#### Health Check
```bash
docker ps  # Check container health status
curl http://localhost:8080/actuator/health
```

---

### Kubernetes Deployment

#### Prerequisites
- Kubernetes cluster (Minikube, GKE, EKS, AKS, etc.)
- kubectl configured

#### Deploy Application

```bash
# Apply all Kubernetes configs
kubectl apply -f k8s/

# Check deployment status
kubectl get deployments
kubectl get pods
kubectl get services
kubectl get hpa
```

#### Access Application

```bash
# Port forward to local machine
kubectl port-forward service/hms-service 8080:80

# Access application
curl http://localhost:8080/actuator/health
```

#### Kubernetes Features

- **High Availability**: 3 replica pods
- **Auto-Scaling**: Horizontal Pod Autoscaler (2-10 pods)
- **Health Checks**: Liveness and readiness probes
- **Load Balancing**: Service-based traffic distribution
- **Resource Management**: CPU and memory limits

#### Scale Manually
```bash
kubectl scale deployment hms-deployment --replicas=5
```

#### View Logs
```bash
kubectl logs -f deployment/hms-deployment
```

#### Delete Deployment
```bash
kubectl delete -f k8s/
```

---

## 📂 Project Structure

### Backend Repository (This Repo)

```
hms/
├── src/
│   ├── main/
│   │   ├── java/com/hms/
│   │   │   ├── HmsApplication.java          # Main entry point
│   │   │   ├── controller/                  # REST Controllers
│   │   │   │   ├── DoctorController.java
│   │   │   │   ├── PatientController.java
│   │   │   │   ├── AppointmentController.java
│   │   │   │   └── BedAvailabilityController.java
│   │   │   ├── service/                     # Business Logic
│   │   │   │   ├── DoctorService.java
│   │   │   │   ├── PatientService.java
│   │   │   │   ├── AppointmentService.java
│   │   │   │   └── BedAvailabilityService.java
│   │   │   ├── repository/                  # Data Access Layer
│   │   │   │   ├── DoctorRepository.java
│   │   │   │   ├── PatientRepository.java
│   │   │   │   ├── AppointmentRepository.java
│   │   │   │   ├── MedicalHistoryRepository.java
│   │   │   │   └── BedAvailabilityRepository.java
│   │   │   ├── domain/                      # JPA Entities
│   │   │   │   ├── Doctor.java
│   │   │   │   ├── Patient.java
│   │   │   │   ├── Appointment.java
│   │   │   │   ├── MedicalHistory.java
│   │   │   │   └── BedAvailability.java
│   │   │   └── config/                      # Configuration
│   │   │       ├── WebConfig.java           # CORS configuration
│   │   │       └── DataSeeder.java          # Database seeder
│   │   └── resources/
│   │       ├── application.properties       # Main configuration
│   │       └── application-prod.properties  # Production config
│   └── test/                                # Test files
├── deployment/                              # Deployment guides
│   ├── FREE_DEPLOYMENT.md                   # Free deployment guide
│   ├── QUICKSTART_DEPLOY.md                 # Quick start guide
│   ├── DEPLOYMENT.md                        # Complete guide
│   └── COMPARISON.md                        # Platform comparison
├── setup-db.sh                              # PostgreSQL setup script
├── render.yaml                              # Render deployment config
├── Procfile                                 # Process file
├── pom.xml                                  # Maven configuration
└── README.md                                # This file
```

### Frontend Repository (Separate)

**Repository:** [https://github.com/yourusername/hms-frontend](https://github.com/yourusername/hms-frontend)

The frontend is a React application with Neo-brutalism design, deployed separately on Vercel.

---

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Manual API Testing
Use the provided test scripts in [`docs/API_TESTING_GUIDE.md`](./docs/API_TESTING_GUIDE.md)

### Health Check Endpoints
```bash
# Overall health
curl http://localhost:8080/actuator/health

# Liveness probe
curl http://localhost:8080/actuator/health/liveness

# Readiness probe
curl http://localhost:8080/actuator/health/readiness
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Varshith Jalla**

- LinkedIn: [Varshith Jalla](https://www.linkedin.com/in/varshith-jalla)
- GitHub: [@Varshith2101](https://github.com/Varshith2101)
- Email: Varshith.web.dev@gmail.com

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- The open-source community for inspiration and tools
- Domain-driven design principles for architectural guidance

---

## 📊 Project Statistics

- **Total API Endpoints:** 31+
- **Database Tables:** 5 (Doctors, Patients, Appointments, Beds, Medical History)
- **Seeded Data:** 15 doctors, 25 patients, 50 appointments, 53 beds
- **Lines of Code:** ~3000+ (Backend + Frontend)
- **Languages:** Java, JavaScript/React, SQL
- **Frameworks:** Spring Boot, Hibernate, React, TailwindCSS
- **Deployment:** Render (Backend), Neon (Database), Vercel (Frontend)
- **Deployment Cost:** $0/month (100% free tier usage)

---

## 🎯 Completed & Future Enhancements

### ✅ Completed
- [x] Create frontend dashboard with React (Neo-brutalism design!)
- [x] Add PostgreSQL database support
- [x] Data seeder with sample data (15 doctors, 25 patients, 50 appointments, 53 beds)
- [x] CORS configuration for frontend-backend communication
- [x] Cloud deployment guides (Render, Railway, Vercel, etc.)

### 🔮 Future Enhancements
- [ ] Add authentication and authorization (Spring Security + JWT)
- [ ] Implement caching with Redis
- [ ] Add API documentation with Swagger/OpenAPI
- [ ] Add email/SMS notifications for appointments
- [ ] Implement audit logging
- [ ] Add comprehensive test coverage
- [ ] Set up CI/CD pipeline
- [ ] Add monitoring with Prometheus/Grafana
- [ ] Implement pagination for large datasets

---

<div align="center">

**⭐ If you find this project useful, please consider giving it a star!**

**🌐 Live Demo:** [https://your-hms-frontend.vercel.app](https://your-hms-frontend.vercel.app)

Made with ❤️ by Varshith Jalla

</div>
