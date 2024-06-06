# Learnify
http://localhost:8080/v1/learnify/swagger-ui/index.html#/
![Learnify Logo](./src/main/resources/assets/learnify.png)

## Project Description

Learnify is an innovative online learning management system designed to facilitate seamless access to educational courses and materials. The platform enables users to register for courses, access content, participate in assessments, and communicate with instructors efficiently. Learnify aims to enhance the online learning experience by providing a robust and user-friendly interface for managing courses, lessons, assignments, and user interactions.

## Features

- **User Management**: Register new users, update user information, and manage user roles.
- **Course Management**: Create, update, and delete courses, and view detailed course information.
- **Lesson Management**: Add new lessons, update lesson content, and organize lessons within courses.
- **Assignment Management**: Create assignments, update assignment details, and manage assignment submissions.
- **Enrollment Management**: Enroll users in courses, view enrollment details, and manage course enrollments.
- **Submission Management**: Handle assignment submissions, grade assignments, and provide feedback.
- **Messaging**: Facilitate communication between users within courses through a built-in messaging system.

## Endpoints

### Users

1. **Register User**
   - **POST /users**
   - **Description**: Create a new user.
   - **Data**: `username`, `password`, `email`, `full_name`, `role`

2. **Get User Information**
   - **GET /users/{user_id}**
   - **Description**: Retrieve detailed information about a specific user.

3. **Update User**
   - **PUT /users/{user_id}**
   - **Description**: Update user information.
   - **Data**: `username`, `password`, `email`, `full_name`

4. **Delete User**
   - **DELETE /users/{user_id}**
   - **Description**: Remove a user.

### Courses

5. **Create Course**
   - **POST /courses**
   - **Description**: Create a new course.
   - **Data**: `course_name`, `description`, `instructor_id`

6. **Get Course Information**
   - **GET /courses/{course_id}**
   - **Description**: Retrieve detailed information about a specific course.

7. **Update Course**
   - **PUT /courses/{course_id}**
   - **Description**: Update course information.
   - **Data**: `course_name`, `description`

8. **Delete Course**
   - **DELETE /courses/{course_id}**
   - **Description**: Remove a course.

9. **Get All Courses**
   - **GET /courses**
   - **Description**: Retrieve a list of all courses.

### Lessons

10. **Create Lesson**
    - **POST /lessons**
    - **Description**: Create a new lesson.
    - **Data**: `lesson_title`, `content`, `course_id`

11. **Get Lesson Information**
    - **GET /lessons/{lesson_id}**
    - **Description**: Retrieve detailed information about a specific lesson.

12. **Update Lesson**
    - **PUT /lessons/{lesson_id}**
    - **Description**: Update lesson information.
    - **Data**: `lesson_title`, `content`

13. **Delete Lesson**
    - **DELETE /lessons/{lesson_id}**
    - **Description**: Remove a lesson.

14. **Get All Lessons of a Course**
    - **GET /courses/{course_id}/lessons**
    - **Description**: Retrieve all lessons for a specific course.

### Assignments

15. **Create Assignment**
    - **POST /assignments**
    - **Description**: Create a new assignment.
    - **Data**: `assignment_title`, `description`, `due_date`, `lesson_id`

16. **Get Assignment Information**
    - **GET /assignments/{assignment_id}**
    - **Description**: Retrieve detailed information about a specific assignment.

17. **Update Assignment**
    - **PUT /assignments/{assignment_id}**
    - **Description**: Update assignment information.
    - **Data**: `assignment_title`, `description`, `due_date`

18. **Delete Assignment**
    - **DELETE /assignments/{assignment_id}**
    - **Description**: Remove an assignment.

19. **Get All Assignments of a Lesson**
    - **GET /lessons/{lesson_id}/assignments**
    - **Description**: Retrieve all assignments for a specific lesson.

### Enrollments

20. **Enroll User in Course**
    - **POST /enrollments**
    - **Description**: Enroll a user in a course.
    - **Data**: `user_id`, `course_id`, `enrollment_date`

21. **Get Enrollment Information**
    - **GET /enrollments/{enrollment_id}**
    - **Description**: Retrieve detailed information about a specific enrollment.

22. **Delete Enrollment**
    - **DELETE /enrollments/{enrollment_id}**
    - **Description**: Remove an enrollment.

23. **Get All Courses Enrolled by a User**
    - **GET /users/{user_id}/courses**
    - **Description**: Retrieve all courses a user is enrolled in.

24. **Get All Users Enrolled in a Course**
    - **GET /courses/{course_id}/users**
    - **Description**: Retrieve all users enrolled in a specific course.

### Submissions

25. **Create Submission**
    - **POST /submissions**
    - **Description**: Create a new submission for an assignment.
    - **Data**: `content`, `submission_date`, `user_id`, `assignment_id`

26. **Get Submission Information**
    - **GET /submissions/{submission_id}**
    - **Description**: Retrieve detailed information about a specific submission.

27. **Update Submission**
    - **PUT /submissions/{submission_id}**
    - **Description**: Update submission information.
    - **Data**: `content`, `grade`

28. **Delete Submission**
    - **DELETE /submissions/{submission_id}**
    - **Description**: Remove a submission.

29. **Get All Submissions of an Assignment**
    - **GET /assignments/{assignment_id}/submissions**
    - **Description**: Retrieve all submissions for a specific assignment.

30. **Get All Submissions of a User**
    - **GET /users/{user_id}/submissions**
    - **Description**: Retrieve all submissions by a specific user.

### Messages

36. **Send Message**
    - **POST /messages**
    - **Description**: Send a message from one user to another within a course.
    - **Data**: `sender_id`, `receiver_id`, `course_id`, `message_content`, `sent_date`

37. **Get Messages of a Course**
    - **GET /courses/{course_id}/messages**
    - **Description**: Retrieve all messages in a specific course.

38. **Get Messages between Users**
    - **GET /messages?sender_id={sender_id}&receiver_id={receiver_id}**
    - **Description**: Retrieve all messages between two specific users.
