# ra-spring-data-provider

A [React Admin](https://marmelab.com/react-admin/) data provider for Spring Boot REST APIs.

This package currently uses `ra-data-json-server` under the hood, making it compatible with Spring Boot backends that follow JSON Server API conventions. The package provides a foundation for future customizations specific to Spring Boot patterns.

## Installation

```bash
npm install ra-spring-data-provider
# or
yarn add ra-spring-data-provider
```

## Usage

```jsx
import * as React from "react";
import { Admin, Resource } from "react-admin";
import raSpringDataProvider from "ra-spring-data-provider";

import { UserList, UserEdit, UserCreate } from "./users";

const App = () => (
  <Admin dataProvider={raSpringDataProvider("http://localhost:8081/api")}>
    <Resource
      name="users"
      list={UserList}
      edit={UserEdit}
      create={UserCreate}
    />
  </Admin>
);

export default App;
```

## API Mapping

This data provider uses the JSON Server API format to communicate with the backend. Your Spring Boot API should follow these conventions:

| React Admin Method | HTTP Method       | URL Example                                                   |
| ------------------ | ----------------- | ------------------------------------------------------------- |
| `getList`          | `GET`             | `http://api.url/users?_sort=name&_order=ASC&_start=0&_end=24` |
| `getOne`           | `GET`             | `http://api.url/users/123`                                    |
| `getMany`          | `GET`             | `http://api.url/users?id=123&id=456`                          |
| `getManyReference` | `GET`             | `http://api.url/users?authorId=345`                           |
| `create`           | `POST`            | `http://api.url/users`                                        |
| `update`           | `PUT`             | `http://api.url/users/123`                                    |
| `updateMany`       | Multiple `PUT`    | `http://api.url/users/123`, `http://api.url/users/456`        |
| `delete`           | `DELETE`          | `http://api.url/users/123`                                    |
| `deleteMany`       | Multiple `DELETE` | `http://api.url/users/123`, `http://api.url/users/456`        |

## Backend Requirements

Your Spring Boot backend must follow the JSON Server API format:

1. **Include the `X-Total-Count` header** in responses to `getList`, `getMany`, and `getManyReference` requests for pagination to work correctly.
2. **Support query parameters** for filtering, sorting, and pagination:
   - `_sort`: field name to sort by
   - `_order`: `ASC` or `DESC`
   - `_start`: index of first item to return
   - `_end`: index of last item to return
   - `id`: for filtering by multiple IDs (can appear multiple times)
   - Other filter parameters as needed

> **Note**: This package currently re-exports `ra-data-json-server`. For more details on the API format, see the [ra-data-json-server documentation](https://github.com/marmelab/react-admin/tree/master/packages/ra-data-json-server).

### Example Spring Boot Controller

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(required = false) String _sort,
            @RequestParam(required = false) String _order,
            @RequestParam(required = false) Integer _start,
            @RequestParam(required = false) Integer _end) {

        // Your logic here
        List<User> users = userService.getUsers(_sort, _order, _start, _end);
        long total = userService.countUsers();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(total))
                .header("Access-Control-Expose-Headers", "X-Total-Count")
                .body(users);
    }
}
```

## Development

### Running the Example

The package includes an example React Admin application in the `example/` directory:

### Running the Example

The package includes an example React Admin application in the `example/` directory:

```bash
npm install
npm run dev
```

The example app will start on `http://localhost:3000` and expects a Spring Boot backend running on `http://localhost:8081`.

### Running Integration Tests

Integration tests use Playwright to test the data provider against a real Spring Boot backend:

```bash
# Install dependencies and Playwright browsers
npm install
npm run install-browsers

# Run tests (requires Spring Boot backend running on port 8081)
npm test

# Run tests in UI mode
npm run test:ui

# Run tests in headed mode (see browser)
npm run test:headed
```

You can also run the complete integration test suite from the project root:

```bash
../run-integration-tests.sh
```

This script will start the Spring Boot backend, run the tests, and clean up automatically.

## License

MIT

## Test Cases

The tests cover:

- ✅ Display users list
- ✅ Create a new user
- ✅ Edit an existing user
- ✅ Delete a user
- ✅ Filter/search users
- ✅ Sort users

## Requirements

- Node.js 18+
- Maven 3.6+
- Java 17+
