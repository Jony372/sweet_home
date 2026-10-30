export interface ErrorResponseModel {
  timestamp: Date;
  status: number;
  error: string;
  message: string;
  path: string;
}
