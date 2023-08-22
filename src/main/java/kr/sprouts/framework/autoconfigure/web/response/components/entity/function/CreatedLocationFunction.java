package kr.sprouts.framework.autoconfigure.web.response.components.entity.function;

import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseResponse;

import java.net.URI;
import java.util.function.Function;

@FunctionalInterface
public interface CreatedLocationFunction extends Function<BaseResponse, URI> { }
