package kr.sprouts.autoconfigure.response.entity.function;

import kr.sprouts.autoconfigure.response.base.BaseResponse;

import java.net.URI;
import java.util.function.Function;

@FunctionalInterface
public interface CreatedLocationFunction extends Function<BaseResponse, URI> { }
