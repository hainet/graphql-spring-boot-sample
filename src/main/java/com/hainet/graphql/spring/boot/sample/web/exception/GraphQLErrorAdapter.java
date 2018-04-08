package com.hainet.graphql.spring.boot.sample.web.exception;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class GraphQLErrorAdapter implements GraphQLError {

    private GraphQLError error;

    public GraphQLErrorAdapter(final GraphQLError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error instanceof ExceptionWhileDataFetching ?
                ((ExceptionWhileDataFetching) error).getException().getMessage() :
                error.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return this.error.getLocations();
    }

    @Override
    public ErrorType getErrorType() {
        return this.error instanceof ExceptionWhileDataFetching &&
                ((ExceptionWhileDataFetching) this.error).getException() instanceof GraphQLError ?
                ((GraphQLError) ((ExceptionWhileDataFetching) this.error).getException()).getErrorType() :
                this.error.getErrorType();
    }

    @Override
    public List<Object> getPath() {
        return this.error.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return this.error.toSpecification();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return this.error.getExtensions();
    }
}
