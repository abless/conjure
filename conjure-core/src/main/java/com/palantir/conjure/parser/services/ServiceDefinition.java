/*
 * (c) Copyright 2018 Palantir Technologies Inc. All rights reserved.
 */

package com.palantir.conjure.parser.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.palantir.conjure.defs.ConjureImmutablesStyle;
import com.palantir.conjure.parser.types.names.ConjurePackage;
import java.util.Map;
import java.util.Optional;
import org.immutables.value.Value;

@JsonDeserialize(as = ImmutableServiceDefinition.class)
@Value.Immutable
@ConjureImmutablesStyle
public interface ServiceDefinition {

    /**
     * Human-readable name of the service.
     *
     * @deprecated unused and will be removed in the future
     */
    // TODO(rfink): This is unused. Remove?
    @Deprecated
    @JsonProperty("name")
    String doNotUseName();

    @JsonProperty("package")
    ConjurePackage conjurePackage();

    Optional<String> docs();

    @JsonProperty("default-auth")
    @Value.Default
    default AuthDefinition defaultAuth() {
        return AuthDefinition.none();
    }

    @JsonProperty("base-path")
    @Value.Default
    default PathDefinition basePath() {
        return PathDefinition.of("/");
    }

    Map<String, EndpointDefinition> endpoints();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableServiceDefinition.Builder {}

}