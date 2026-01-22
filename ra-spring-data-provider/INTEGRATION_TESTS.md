# Integration Test Suite Documentation

## Overview

This test suite provides comprehensive integration testing for the React Admin data provider library. The data provider currently uses `ra-data-json-server` under the hood, and these tests verify the integration with a Spring Boot backend that follows JSON Server API conventions.

The tests are organized into five main categories:

## Test Files

### 1. **users.spec.js** (Original Tests)

Basic CRUD operations and user management functionality.

**Test Coverage:**

- Display users list
- Create a new user
- Edit an existing user
- Update multiple users (bulk update)
- Delete a user
- Delete multiple users (bulk delete)
- Filter users by search
- Sort users by name

**Key Features Tested:**

- List display with DataGrid
- Form submissions (create/edit)
- Bulk operations with custom dialog
- Filtering and sorting
- Navigation between pages

---

### 2. **data-provider.spec.js** (New)

Tests for the data provider integration with the backend. These tests verify that all React Admin data provider methods work correctly with a Spring Boot API that follows JSON Server conventions.

**Test Coverage:**

- `getList` - Fetch list with pagination
- `getOne` - Fetch single record
- `getMany` - Fetch multiple records by IDs
- `getManyReference` - Fetch referenced records
- `create` - Create new record
- `update` - Update existing record
- `updateMany` - Bulk update operation
- `delete` - Delete single record
- `deleteMany` - Bulk delete operation
- Sorting and pagination parameters
- Header validation (X-Total-Count)

**Key Features Tested:**

- All React Admin data provider methods
- Query parameter handling
- Pagination functionality
- Sorting operations
- API response validation

---

### 3. **error-handling.spec.js** (New)

Tests for error handling and edge cases.

**Test Coverage:**

- Empty list display
- Required field validation
- Email format validation
- Very long input values
- Special characters in input
- Navigation with unsaved changes
- Rapid successive operations
- Non-existent record handling
- Concurrent bulk operations
- Page refresh state maintenance
- Duplicate email handling
- Empty string inputs

**Key Features Tested:**

- Form validation
- Error message display
- Graceful degradation
- Data integrity
- Edge case handling
- Application stability

---

### 4. **performance.spec.js** (New)

Tests for performance and load handling.

**Test Coverage:**

- Page load time benchmarks
- Large dataset pagination
- Rapid navigation between pages
- Concurrent user creations
- Bulk update performance
- Bulk delete performance
- Sorting operation speed
- Filter operation speed
- Complex query handling
- Form submission latency

**Performance Benchmarks:**

- Page load: < 5 seconds
- Pagination: < 3 seconds
- Sorting: < 3 seconds
- Filtering: < 2 seconds
- Form submission: < 3 seconds average
- Bulk operations: < 10 seconds

**Key Features Tested:**

- Response times
- Load handling
- Concurrent operations
- UI responsiveness

---

### 5. **ui-ux.spec.js** (New)

Tests for user interface and user experience.

**Test Coverage:**

- Page titles and headings
- Navigation accessibility
- Form labels and fields
- Save and cancel buttons
- Row selection highlighting
- Bulk action buttons visibility
- Table structure and display
- Edit and delete buttons
- Dialog displays
- Success notifications
- Responsive layout
- Loading states
- Button states (enabled/disabled)
- Email as mailto link
- Keyboard navigation
- Empty states
- Pagination info
- Role display styling

**Key Features Tested:**

- UI components visibility
- Accessibility features
- User interaction flows
- Visual feedback
- Navigation patterns
- Form usability

---

## Running the Tests

### Run All Tests

```bash
npm test
```

### Run Specific Test File

```bash
npm test -- users.spec.js
npm test -- data-provider.spec.js
npm test -- error-handling.spec.js
npm test -- performance.spec.js
npm test -- ui-ux.spec.js
```

### Run Tests with UI

```bash
npm run test:ui
```

### Run Tests in Headed Mode

```bash
npm run test:headed
```

### Run Tests by Category

```bash
# Run only data provider tests
npm test -- data-provider

# Run only performance tests
npm test -- performance

# Run only UI tests
npm test -- ui-ux

# Run only error handling tests
npm test -- error-handling
```

### Debug Specific Test

```bash
npm test -- --debug --grep "should display users list"
```

---

## Test Environment

### Prerequisites

1. **Backend Server**: Spring Boot application must be running on `http://localhost:8081/api` and follow JSON Server API format
2. **Frontend Dev Server**: Vite dev server will start automatically on `http://localhost:3000`
3. **Browser**: Chromium (installed via Playwright)

> **Note**: The backend must support JSON Server API conventions including the `X-Total-Count` header and query parameters like `_sort`, `_order`, `_start`, and `_end`.

### Installation

```bash
# Install dependencies
npm install

# Install Playwright browsers
npm run install-browsers
```

---

## Configuration

### Playwright Config (`playwright.config.js`)

- **Base URL**: `http://localhost:3000`
- **Test Directory**: `./tests`
- **Workers**: 1 (sequential execution)
- **Retries**: 0 locally, 2 in CI
- **Reporter**: HTML
- **Screenshots**: Only on failure
- **Trace**: On first retry

### Test Structure

All tests follow the pattern:

```javascript
test.describe("Test Category", () => {
  test.beforeEach(async ({ page }) => {
    await page.goto("/");
    await page.waitForLoadState("networkidle");
  });

  test("should do something", async ({ page }) => {
    // Test implementation
  });
});
```

---

## Best Practices

### Test Isolation

- Each test creates its own test data with unique timestamps
- Tests don't depend on each other
- Data cleanup is handled naturally through the lifecycle

### Wait Strategies

- Use `waitForLoadState("networkidle")` for page loads
- Use `waitForSelector` for specific elements
- Use `waitForTimeout` sparingly and only when necessary

### Assertions

- Use Playwright's built-in assertions for better error messages
- Use `expect().toBeVisible()` instead of checking existence
- Use `expect().toContainText()` for partial matches

### Error Handling

- Use `.catch(() => false)` for optional checks
- Always verify the app is still functional after errors
- Test graceful degradation

---

## CI/CD Integration

### GitHub Actions Example

```yaml
name: Integration Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-node@v3
        with:
          node-version: "18"
      - name: Install dependencies
        run: npm ci
      - name: Install Playwright
        run: npx playwright install chromium
      - name: Start Backend
        run: |
          cd ra-spring-json-server
          mvn spring-boot:run &
          sleep 30
      - name: Run tests
        run: npm test
      - uses: actions/upload-artifact@v3
        if: always()
        with:
          name: playwright-report
          path: playwright-report/
```

---

## Troubleshooting

### Tests Failing

1. **Backend not running**: Ensure Spring Boot app is running on port 8081
2. **Port conflicts**: Make sure ports 3000 and 8081 are available
3. **Timing issues**: Increase timeouts in slow environments
4. **Browser issues**: Re-run `npm run install-browsers`

### Slow Tests

1. Reduce the number of test users created
2. Run tests sequentially (already configured)
3. Use `--grep` to run specific tests
4. Check backend performance

### Flaky Tests

1. Increase wait times if needed
2. Check network conditions
3. Verify backend is stable
4. Run tests individually to isolate issues

---

## Coverage Summary

### Total Tests: ~70+

**By Category:**

- User Management: 8 tests
- Data Provider: 10 tests
- Error Handling: 13 tests
- Performance: 10 tests
- UI/UX: 18 tests

**Coverage Areas:**

- ✅ CRUD Operations
- ✅ Bulk Operations
- ✅ Pagination
- ✅ Sorting
- ✅ Filtering
- ✅ Form Validation
- ✅ Error Handling
- ✅ Performance Benchmarks
- ✅ UI Components
- ✅ Accessibility
- ✅ Navigation
- ✅ Data Integrity

---

## Future Enhancements

1. **Visual Regression Testing**: Add screenshot comparisons
2. **API Mocking**: Test offline scenarios
3. **Accessibility Testing**: Add axe-core integration
4. **Mobile Testing**: Add mobile viewport tests
5. **Cross-browser Testing**: Add Firefox and WebKit
6. **Load Testing**: Add stress testing with many users
7. **Security Testing**: Add XSS and injection tests
8. **Internationalization**: Add i18n tests

---

## Contributing

When adding new tests:

1. Follow the existing file structure
2. Use descriptive test names
3. Include comments for complex logic
4. Ensure tests are isolated
5. Add documentation for new test categories
6. Update this README with new coverage

---

## Support

For issues or questions:

- Check the [Playwright documentation](https://playwright.dev)
- Review the [React Admin documentation](https://marmelab.com/react-admin)
- Open an issue in the project repository
