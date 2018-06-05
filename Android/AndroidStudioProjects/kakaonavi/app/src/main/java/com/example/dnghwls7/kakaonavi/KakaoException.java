/**
 * Copyright 2014-2015 Kakao Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.dnghwls7.kakaonavi;

/**
 * general exception
 */
public class KakaoException extends RuntimeException {
    private static final long serialVersionUID = 3738785191273730776L;
    private ErrorType errorType;

    public enum ErrorType {
        ILLEGAL_ARGUMENT,
        ILLEGAL_STATE,
        MISS_CONFIGURATION,
        CANCELED_OPERATION,
        AUTHORIZATION_FAILED,
        UNSPECIFIED_ERROR,
        JSON_PARSING_ERROR,
        URI_LENGTH_EXCEEDED,
        KAKAOTALK_NOT_INSTALLED,
    }

    public KakaoException(final ErrorType errorType) {
        super();
        this.errorType = errorType;
    }

    public KakaoException(final ErrorType errorType, final String msg) {
        super(msg);
        this.errorType = errorType;
    }

    public boolean isCancledOperation() {
        return errorType == ErrorType.CANCELED_OPERATION;
    }

    public KakaoException(final String msg) {
        super(msg);
    }

    public KakaoException(final Throwable t) {
        super(t);
    }

    public KakaoException(final String s, final Throwable t) {
        super(s, t);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String toString() {
        return getErrorType() == null ? super.getMessage() : getErrorType() + ": " + super.getMessage();
    }
}
