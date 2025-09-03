# 📝 Blog Backend (Spring MVC + MySQL + JWT)

This is the **backend REST API** for a Blog Application built using **Spring Core MVC**. It uses **Spring JDBC** with **MySQL** as the database and follows a clean layered architecture (**DAO + Service + Controller**).

Authentication is implemented with **JWT tokens** and secured using a **Spring Interceptor**. The backend also supports **image uploads** for blog posts.

The frontend is built separately in **React**, which consumes these REST APIs.

---

## 🚀 Features

* 👤 User authentication with **JWT tokens** (`/user` endpoints)
* ✍️ Blog CRUD operations (`/` endpoints)
* 📂 Upload and manage images for blogs
* 🔐 JWT validation via **Spring Interceptor**
* 🗄️ DAO + Service layer for scalability
* 💾 MySQL database with **Spring JDBC**

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Core MVC, Spring JDBC
* **Database:** MySQL
* **Security:** JWT + Interceptor
* **Frontend (separate):** React.js

---

## 📂 Project Structure

```
src/main/java/com/blog
│── controller/        # REST Controllers (User + Blog)
│── service/           # Business logic
│── dao/               # Database access (Spring JDBC)
│── model/             # POJOs (User, Blog, etc.)
│── interceptor/       # JWT Interceptor
│── config/            # Spring configuration
│── utils/             # JWT utils, helpers
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/blog-backend.git
cd blog-backend
```

### 2️⃣ Configure Database

* Create MySQL DB (e.g., `blog_db`)
* Update DB credentials in `application.properties` (or `db.properties`):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 3️⃣ Run the Backend

* Deploy on Tomcat or run from IDE
* Backend runs at:

```
http://localhost:8080/
```

---

## 🔑 Security

* Public endpoints: `/user/login`, `/user/register`
* Secured endpoints: `/blogs/**` (require JWT)
* **JWT Interceptor** checks tokens before allowing access

---

## 📸 Image Upload

* Users can upload images with their blog posts
* Images stored in `/uploads` folder
* File reference stored in MySQL with blog data

---

## 📌 API Endpoints

### 👤 User (Authentication) → `/user`

* `POST /user/register` → Register a new user
* `POST /user/login` → Login and get JWT token

### 📝 Blogs (Protected) → `/`

* `GET /` → Get all blogs
* `GET /{id}` → Get blog by ID
* `POST /` → Create new blog (requires JWT + image upload)
* `PUT /{id}` → Update existing blog (requires JWT)
* `DELETE /{id}` → Delete blog (requires JWT)

---

## 📌 Future Enhancements

* Add **role-based access (Admin/User)**
* Add **comments, likes & categories**
* Store images in **cloud storage (S3/Cloudinary)**
* Add **pagination & search filters**

---

## 👨‍💻 Author

**Tushar Shitole**
MERN & Java Full Stack Developer

---

👉 Do you want me to also include **example request/response JSON (for login & blog creation with JWT + image upload)** inside the README? That would make it super clear for anyone testing your APIs.
