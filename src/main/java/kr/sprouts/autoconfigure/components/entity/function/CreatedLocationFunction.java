package kr.sprouts.autoconfigure.components.entity.function;

import kr.sprouts.autoconfigure.components.base.BaseResponse;

import java.net.URI;
import java.util.function.Function;

@FunctionalInterface
public interface CreatedLocationFunction extends Function<BaseResponse, URI> { }
