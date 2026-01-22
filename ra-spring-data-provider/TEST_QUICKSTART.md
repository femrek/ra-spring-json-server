# Integration Tests - Quick Start Guide

## Setup

### 1. Install Dependencies

```bash
cd ra-spring-data-provider
npm install
npm run install-browsers
```

### 2. Start the Backend Server

```bash
cd ra-spring-json-server
./mvnw spring-boot:run
```

The backend will start on `http://localhost:8081`

### 3. Run the Tests

In a new terminal:

```bash
cd ra-spring-data-provider
npm test
```

## Quick Commands

### Run All Tests

```bash
npm test
```

### Run Tests with Visual UI

```bash
npm run test:ui
```

### Run Specific Test Categories

```bash
npm run test:users              # Basic CRUD operations
npm run test:data-provider      # Data provider methods
npm run test:error-handling     # Error scenarios
npm run test:performance        # Performance benchmarks
npm run test:ui-ux             # UI/UX functionality
```

### Debug a Failing Test

```bash
npm run test:debug
```

### View Test Report

```bash
npm run test:report
```

## Test Organization

The tests are organized into 5 categories:

1. **users.spec.js** - Basic user management CRUD operations
2. **data-provider.spec.js** - Data provider API integration
3. **error-handling.spec.js** - Error handling and edge cases
4. **performance.spec.js** - Performance and load testing
5. **ui-ux.spec.js** - User interface and experience

## What Gets Tested

✅ **CRUD Operations**: Create, Read, Update, Delete users  
✅ **Bulk Operations**: Update and delete multiple users at once  
✅ **Pagination**: Navigate through pages of data  
✅ **Sorting**: Sort by different columns  
✅ **Filtering**: Search and filter users  
✅ **Validation**: Form validation and error messages  
✅ **Performance**: Page load times and operation speed  
✅ **UI Components**: Buttons, forms, tables, dialogs  
✅ **Accessibility**: Keyboard navigation and labels  
✅ **Error Handling**: Graceful error recovery

## Expected Results

- **Total Tests**: ~70+ tests across all categories
- **Typical Run Time**: 5-10 minutes for full suite
- **Performance Benchmarks**:
  - Page load: < 5 seconds
  - Sorting/filtering: < 3 seconds
  - Form submission: < 3 seconds
  - Bulk operations: < 10 seconds

## Troubleshooting

### "Connection refused" errors

- Make sure the Spring Boot backend is running on port 8081
- Check if port 3000 is available for the frontend

### Tests are slow

- Run individual test files instead of the full suite
- Use `npm run test:ui` to run tests selectively

### Tests fail intermittently

- Increase timeouts in your environment
- Check network connectivity
- Ensure backend database is accessible

### Browser not found

```bash
npm run install-browsers
```

## CI/CD Integration

The tests are designed to run in CI/CD pipelines:

```bash
# In your CI script
npm ci
npx playwright install chromium
# Start backend (background)
npm test
```

## Next Steps

- Review [INTEGRATION_TESTS.md](./INTEGRATION_TESTS.md) for detailed documentation
- Check [playwright.config.js](./playwright.config.js) for configuration
- View individual test files in the [tests/](./tests/) directory

## Need Help?

- [Playwright Documentation](https://playwright.dev)
- [React Admin Documentation](https://marmelab.com/react-admin)
- [Project Issues](https://github.com/your-repo/issues)
