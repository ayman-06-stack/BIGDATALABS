# 🎓 Derb School - School Management System

Complete school management platform built with Laravel for student, teacher, and course administration.

## 📖 About

Derb School is a comprehensive web-based school management system developed with Laravel framework. It provides complete solutions for managing students, teachers, courses, grades, attendance, and schedules in a modern, intuitive interface.

## ✨ Features

### 🔐 Authentication & Authorization
- ✅ Secure user authentication
- ✅ Role-based access control (Admin, Teacher, Student, Parent)
- ✅ Session management
- ✅ Password reset functionality

### 👥 Student Management
- ✅ Student registration and enrollment
- ✅ Update student information
- ✅ Track academic progress
- ✅ View student history
- ✅ Manage student documents

### 👨‍🏫 Teacher Management
- ✅ Add and manage teachers
- ✅ Assign teachers to courses
- ✅ Manage teaching schedules
- ✅ Track teacher performance

### 📚 Course Management
- ✅ Create and manage courses
- ✅ Assign course instructors
- ✅ Set course schedules
- ✅ Manage course materials
- ✅ Track course enrollment

### 📅 Attendance Tracking
- ✅ Daily attendance marking
- ✅ Attendance reports
- ✅ Absence notifications
- ✅ Attendance analytics

### 📊 Grades & Results
- ✅ Record grades and marks
- ✅ Calculate GPA
- ✅ Generate report cards
- ✅ Performance analytics
- ✅ Grade distribution charts

### 🏫 Class Management
- ✅ Create and manage classes
- ✅ Assign students to classes
- ✅ Class schedules
- ✅ Class-wise performance

### 📢 Communication
- ✅ Announcements and notices
- ✅ Parent notifications
- ✅ Message board
- ✅ Event calendar

## 🛠️ Technology Stack

### Backend
- **Laravel 10.x**: PHP web framework
- **PHP 8.1+**: Server-side language
- **Blade**: Template engine

### Frontend
- **Blade Templates**: Server-side rendering
- **HTML5/CSS3**: Structure and styling
- **JavaScript**: Interactive features
- **Bootstrap**: Responsive design

### Database
- **MySQL 8.0**: Relational database
- **Eloquent ORM**: Database abstraction

### Additional Tools
- **Composer**: PHP package manager
- **Laravel Artisan**: Command-line interface
- **Migrations**: Database version control

## 🚀 Installation

### Prerequisites
```
✓ PHP 8.1 or higher
✓ Composer
✓ MySQL 5.7 or higher
✓ Git
✓ Node.js & npm (optional, for frontend assets)
```

### Installation Steps

#### 1. Clone Repository
```bash
git clone https://github.com/ayman-06-stack/Derb-school.git
cd Derb-school
```

#### 2. Install Dependencies
```bash
# Install PHP dependencies
composer install

# Install NPM dependencies (if needed)
npm install
npm run dev
```

#### 3. Configure Environment
```bash
# Copy environment file
cp .env.example .env

# Generate application key
php artisan key:generate

# Generate JWT secret (if using JWT)
php artisan jwt:secret
```

#### 4. Database Setup
```bash
# Configure database credentials in .env
# DB_CONNECTION=mysql
# DB_HOST=127.0.0.1
# DB_PORT=3306
# DB_DATABASE=derb_school
# DB_USERNAME=root
# DB_PASSWORD=

# Run migrations
php artisan migrate

# Seed sample data (optional)
php artisan db:seed
```

#### 5. Start Application
```bash
# Clear cache
php artisan config:cache

# Start development server
php artisan serve

# Access at http://localhost:8000
```

## 📋 Database Schema

### Key Tables

```sql
-- Users table
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    role ENUM('admin', 'teacher', 'student', 'parent'),
    password VARCHAR(255),
    created_at TIMESTAMP
);

-- Students table
CREATE TABLE students (
    id BIGINT PRIMARY KEY,
    user_id BIGINT,
    enrollment_number VARCHAR(50) UNIQUE,
    class_id BIGINT,
    date_of_birth DATE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Courses table
CREATE TABLE courses (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    code VARCHAR(50) UNIQUE,
    teacher_id BIGINT,
    credits INT,
    FOREIGN KEY (teacher_id) REFERENCES users(id)
);

-- Grades table
CREATE TABLE grades (
    id BIGINT PRIMARY KEY,
    student_id BIGINT,
    course_id BIGINT,
    mark DECIMAL(5,2),
    grade VARCHAR(2),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Attendance table
CREATE TABLE attendance (
    id BIGINT PRIMARY KEY,
    student_id BIGINT,
    course_id BIGINT,
    date DATE,
    status ENUM('present', 'absent', 'late'),
    FOREIGN KEY (student_id) REFERENCES students(id)
);

-- Classes table
CREATE TABLE classes (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    section VARCHAR(50),
    capacity INT,
    created_at TIMESTAMP
);
```

## 👥 User Roles & Permissions

### 🔑 Admin
- Full system access
- Manage users and roles
- System configuration
- Reports and analytics

### 👨‍🏫 Teacher
- Manage assigned courses
- Record grades and attendance
- Upload course materials
- Communicate with students

### 👨‍🎓 Student
- View courses and schedules
- Check grades and attendance
- Access course materials
- View announcements

### 👨‍👩‍👧 Parent
- Monitor child's progress
- View attendance and grades
- Receive notifications

## 🔄 Main Workflows

### Student Enrollment
```
1. Register student account
2. Assign to class
3. Enroll in courses
4. Activate account
```

### Grade Management
```
1. Teacher enters grades
2. Automatic GPA calculation
3. Generate report cards
4. Parent notification
```

### Attendance Tracking
```
1. Teacher marks attendance
2. System tracks percentage
3. Automatic warnings if low
4. Generate attendance reports
```

## 📈 Key Functionalities

### Dashboard
- Overview of key metrics
- Recent activities
- Quick statistics
- Upcoming events

### Reports
- Student progress reports
- Attendance reports
- Grade distribution analysis
- Performance trends

### Analytics
- Student performance analytics
- Attendance patterns
- Course performance metrics
- Teacher effectiveness

## 🎨 UI/UX Features

- Responsive design (Mobile, Tablet, Desktop)
- Intuitive navigation
- Real-time notifications
- Dark mode support
- Accessibility compliance

## 🔒 Security Features

- SQL injection prevention
- CSRF protection
- XSS prevention
- Password hashing (bcrypt)
- Role-based access control
- Audit logging

## 🧪 Testing

```bash
# Run tests
php artisan test

# Run specific test
php artisan test --filter=StudentTest

# Generate coverage report
php artisan test --coverage
```

## 🚀 Deployment

### Server Requirements
- Web server (Apache/Nginx)
- PHP 8.1+
- MySQL database
- SSL certificate

### Deployment Steps
1. Upload files to server
2. Configure .env file
3. Run migrations
4. Set proper permissions
5. Enable HTTPS

## 📚 API Documentation

### Authentication Endpoints
```
POST   /api/auth/login
POST   /api/auth/register
POST   /api/auth/logout
GET    /api/auth/profile
```

### Student Endpoints
```
GET    /api/students
POST   /api/students
GET    /api/students/{id}
PUT    /api/students/{id}
DELETE /api/students/{id}
```

### Course Endpoints
```
GET    /api/courses
POST   /api/courses
GET    /api/courses/{id}
PUT    /api/courses/{id}
DELETE /api/courses/{id}
```

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create feature branch (`git checkout -b feature/NewFeature`)
3. Commit changes (`git commit -m 'Add NewFeature'`)
4. Push to branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

## 📝 License

MIT License - See LICENSE file for details

## 👤 Author

**Ayman Guendouz**
- GitHub: [@ayman-06-stack](https://github.com/ayman-06-stack)
- Email: aymanguendouz07@gmail.com
- LinkedIn: [Ayman Guendouz](https://www.linkedin.com/in/ayman-guendouz)

## 🆘 Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Email: aymanguendouz07@gmail.com
- Check existing documentation

---

⭐ **If this project helped you, please leave a star!** ⭐
