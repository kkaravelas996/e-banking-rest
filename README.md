[README.md](https://github.com/user-attachments/files/22329471/README.md)
#  E-Banking App

Full-stack εφαρμογή e-banking με **Spring Boot backend** και **React frontend**.

---

##  Δομή έργου
```
e-banking-app/
 ├── backend/   # Spring Boot REST API (Java 17, Spring Security, JWT, MySQL)
 └── frontend/  # React (Vite, Axios, Context API)
```

---

##  Οδηγίες Build & Deploy

### 🔹 Backend (Spring Boot)

1. Μετακίνηση στον φάκελο:
   ```bash
   cd backend
   ```

2. Build με Maven:
   ```bash
   mvn clean install
   ```

3. Εκκίνηση εφαρμογής:
   ```bash
   mvn spring-boot:run
   ```
   ή
   ```bash
   java -jar target/e-banking-rest-0.0.1-SNAPSHOT.jar
   ```

4. Το backend τρέχει στο:
   ```
   http://localhost:8080/api
   ```

.

---

### 🔹 Frontend (React + Vite)

1. Μετακίνηση στον φάκελο:
   ```bash
   cd frontend
   ```

2. Εγκατάσταση dependencies:
   ```bash
   npm install
   ```

3. Εκκίνηση development server:
   ```bash
   npm run dev
   ```
   Το frontend τρέχει στο:
   ```
   http://localhost:5173
   ```

4. Δημιουργία production build:
   ```bash
   npm run build
   ```
   Τα αρχεία παραγωγής θα βρίσκονται στον φάκελο `dist/`.

---

## ⚡ Deployment

- **Backend**:  
  - Τρέχει standalone με το JAR (`java -jar ...`)   

- **Frontend**:  
  - Μετά το `npm run build`, το περιεχόμενο του `frontend/dist/` μπορεί να σερβιριστεί από web server ή ακόμα και από το ίδιο το Spring Boot (ως static resources).  

---

## 🔑 Authentication & Roles
- Login: `POST /api/auth/login`
- Register:  
  - `POST /api/registerAsCustomer` → εγγραφή πελάτη  
  - `POST /api/registerAsAdmin` → εγγραφή διαχειριστή
- Users: `GET /api/users` → προσβάσιμο μόνο από **ADMIN**
- Transfer: `POST /api/transfer` → προσβάσιμο μόνο από **CUSTOMER**

---

##  Τεχνολογίες
- **Backend**: Java 17, Spring Boot, Spring Security, JWT, JPA/Hibernate, MySQL  
- **Frontend**: React (Vite), React Router, Axios, Context API  
