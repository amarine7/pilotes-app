package com.great.MiquelMontoro.pilotes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ErrorResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T09:06:59.005Z[GMT]")

@Builder
public class ErrorResponse   {
    @JsonProperty("timestamp")
    private String timestamp = null;

    @JsonProperty("error")
    private String error = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("fieldErrors")
    private List<String> fieldErrors = null;

    public ErrorResponse timestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     * @return timestamp
     **/
    @Schema(description = "")

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorResponse error(String error) {
        this.error = error;
        return this;
    }

    /**
     * Get error
     * @return error
     **/
    @Schema(description = "")

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorResponse status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     * @return status
     **/
    @Schema(description = "")

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ErrorResponse fieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }

    public ErrorResponse addFieldErrorsItem(String fieldErrorsItem) {
        if (this.fieldErrors == null) {
            this.fieldErrors = new ArrayList<String>();
        }
        this.fieldErrors.add(fieldErrorsItem);
        return this;
    }

    /**
     * Get fieldErrors
     * @return fieldErrors
     **/
    @Schema(description = "")

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse errorResponse = (ErrorResponse) o;
        return Objects.equals(this.timestamp, errorResponse.timestamp) &&
                Objects.equals(this.error, errorResponse.error) &&
                Objects.equals(this.status, errorResponse.status) &&
                Objects.equals(this.fieldErrors, errorResponse.fieldErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, error, status, fieldErrors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponse {\n");

        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    error: ").append(toIndentedString(error)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    fieldErrors: ").append(toIndentedString(fieldErrors)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
