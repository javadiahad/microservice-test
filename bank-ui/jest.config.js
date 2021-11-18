require('jest-preset-angular/ngcc-jest-processor');
const { pathsToModuleNameMapper } = require('ts-jest/utils');
const { paths } = require('./tsconfig.json').compilerOptions;

/** @type {import('ts-jest/dist/types').InitialOptionsTsJest} */
module.exports = {
  preset: 'jest-preset-angular',
  roots: ['<rootDir>/src/'],
  testMatch: ['**/+(*.)+(spec).+(ts)'],
  moduleNameMapper: pathsToModuleNameMapper(paths, { prefix: '<rootDir>' }),
  setupFilesAfterEnv: ['<rootDir>/setup-jest.ts'],
  collectCoverage: true,
  coverageReporters: ['html'],
  coverageDirectory: 'coverage/my-app',
  testURL: 'http://127.0.0.1:1234',  
};