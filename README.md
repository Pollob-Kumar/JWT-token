# 🔐 JWT Token — A Simple Learning Project

> **This project is built purely to understand how JWT works.**
> There is no Database, no Security and that's intentional.
> The only goal is to clearly understand how a JWT token is **generated** and **how to use it**.

---

## 🧠 What is JWT? (In One Line)

When you **Login**, the server gives you a **Token**.
Whenever you want to access something protected, you send that token.
The server reads the token, knows who you are, and grants access.

```
Login → Get Token → Use Token to access protected routes
```

---

## 📁 Project Structure

```
src/main/java/com/pollob/jwt/
│
├── controller
│   └── AuthController.java        ← All logic for creating and reading tokens
├── user
│   └── User.java                  ← Holds only email and password
├── security
│   └── JwtUtil.java               ← 3 API endpoints (register, login, profile)
└── JwtTokenTestApplication.java   ← Main method (run file)
```

> Only 3 files. Intentionally kept small so it's easy to follow.

---

## ⚙️ How to Run

### Step 1 — Clone the project

```bash
git clone https://github.com/Pollob-Kumar/JWT-token
cd JWT-token
```

### Step 2 — Run

```bash
./mvnw spring-boot:run
```

Server starts at → `http://localhost:8080`

> Requires **Java 17+** and **Maven**.

---

## 🛠️ API — How to Test

Use [Postman](https://www.postman.com/) or any API client to test the endpoints below.

---

### 1️⃣ Register — Create a new user

```
Method : POST
URL    : http://localhost:8080/register
```

**Body (JSON):**

```json
{
  "email": "Pollob@gmail.com",
  "password": "123"
}
```

**Response:**

```
Registration done! Email: Pollob@gmail.com
```

---

### 2️⃣ Login — Get your JWT Token

```
Method : POST
URL    : http://localhost:8080/login
```

**Body (JSON):**

```json
{
  "email": "Pollob@gmail.com",
  "password": "123"
}
```

**Response:**

```
Your token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWtpYkBnbWFpbC5jb20i...
```

> 🔑 This long string is your **JWT Token**. You'll need it in the next step.

---

### 3️⃣ Profile — Access a Protected Route Using the Token

```
Method : GET
URL    : http://localhost:8080/profile
```

**Header:**

```
Key   : token
Value : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWtpYkBnbWFpbC5jb20i...
        ↑ Paste the token you received from Login
```

**Response (valid token):**

```
Hello Pollob@gmail.com! You have successfully accessed this route using a token!
```

**Response (wrong or fake token):**

```
Error: JWT signature does not match ❌
```

---

## 🔍 What's Inside a JWT Token?

After login, the token looks like this:

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWtpYkBnbWFpbC5jb20ifQ.xyz123
```

It has 3 parts, separated by a **dot (.)**:

| Part | Name      | Contains                              |
| ---- | --------- | ------------------------------------- |
| 1st  | Header    | Algorithm name                        |
| 2nd  | Payload   | Your email, created time, expiry time |
| 3rd  | Signature | Tamper-proof seal                     |

👉 Paste your token at [jwt.io](https://jwt.io) to see exactly what's inside.

---

## ❓ FAQ

**Q: Why is there no Database?**
A: The goal is to understand how JWT works. Adding a DB would make the code complex and distracting.

**Q: Why is there no Security?**
A: Adding Spring Security brings a lot of extra boilerplate that can confuse beginners. This project focuses only on the core concept of JWT.

**Q: Can this be used in Production?**
A: No. Production apps require a Database, Spring Security, HTTPS, HttpOnly Cookies, and more. This is for learning only.

**Q: How long is the token valid?**
A: 15 minutes. After that, you need to login again to get a new token.

---

## 🗺️ What to Learn Next

Once you understand this project, here are the next steps:

- [ ] Save users to a MySQL database
- [ ] Hash passwords with BCrypt
- [ ] Protect routes using Spring Security
- [ ] Add Refresh Token support

---

## 📄 License

This project is for **learning purposes only**.
