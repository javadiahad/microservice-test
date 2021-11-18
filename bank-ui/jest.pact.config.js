module.exports = {
  testMatch: [ "**/+(*.)+(pact)\.(spec)\.(ts)" ],
  transform: {
    '^.+\\.(ts|js|html)$': 'ts-jest'
  },
  globals: {
    'ts-jest': {
      tsConfig: '<rootDir>/tsconfig.spec.json'
    },
    stringifyContentPathRegex: true
  },
  moduleFileExtensions: ['ts', 'js', 'html'],
  testURL: 'http://127.0.0.1:8020',
  setupFilesAfterEnv: ['<rootDir>/pact/jest/setupJest.ts']
};
