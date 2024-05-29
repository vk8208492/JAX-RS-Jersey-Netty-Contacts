### Get All Users

**Endpoint:** GET `http://<your_host>:<your_port>/api/v1/users/all`

**Description:**
This endpoint retrieves all users from the system.

**Parameters:**
None

**Response:**
- `200 OK`: Successfully retrieved all users.
- `500 Internal Server Error`: Failed to retrieve data due to an internal server error.

### Get One User

**Endpoint:** GET `http://<your_host>:<your_port>/api/v1/users/one?id=<user_id>`

**Description:**
This endpoint retrieves one user with specific id from the system.

**Parameters(Query):**
`id=<user_id>` required, number. User id.

**Response:**
- `200 OK`: Successfully retrieved all user.
- `404 Not Found`: Failed to retrieve data due to not found.
- `500 Internal Server Error`: Failed to retrieve data due to an internal server error.

### Create User

**Endpoint:** POST `http://<your_host>:<your_port>/api/v1/users/create`

**Description:**
This endpoint creates a new user in the system.

**Body:**
```json
{
    "firstName": "required, string. User first name",
    "lastName": "required, string. User last name",
    "email": "required, string. User email",
    "phone": "required, string. User phone number. E.164 format"
}
```

**Response:**
- `201 Create`: Successfully create user.
- `409 Conflict`: Failed to create users due to conflict.
- `500 Internal Server Error`: Failed to create due to an internal server error.

### Update User

**Endpoint:** PUT `http://<your_host>:<your_port>/api/v1/users/update`

**Description:**
This endpoint update exists user in the system.

**Body:**
```json
{
    "id": "required, number. User id",
    "firstName": "optional, string. User first name",
    "lastName": "optional, string. User last name",
    "email": "optional, string. User email",
    "phone": "optional, string. User phone number. E.164 format"
}
```

**Response:**
- `202 Accepted`: Successfully update user.
- `409 Conflict`: Failed to update users due to conflict.
- `404 Not Found`: Failed to update users due to not found.
- `500 Internal Server Error`: Failed to update due to an internal server error.

### Delete User

**Endpoint:** DELETE `http://<your_host>:<your_port>/api/v1/users/delete?id=<user_id>`

**Description:**
This endpoint delete exists user in the system.

**Parameters(Query):**
`id=<user_id>` required, number. User id.

**Response:**
- `204 No Content`: Successfully delete user.
- `409 Conflict`: Failed to delete users due to conflict.
- `404 Not Found`: Failed to delete users due to not found.
- `500 Internal Server Error`: Failed to delete due to an internal server error.