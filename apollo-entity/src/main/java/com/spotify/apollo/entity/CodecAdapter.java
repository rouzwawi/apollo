/*
 * -\-\-
 * Spotify Apollo Entity Middleware
 * --
 * Copyright (C) 2013 - 2018 Spotify AB
 * --
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -/-/-
 */
package com.spotify.apollo.entity;

import static java.util.Objects.requireNonNull;

import com.spotify.apollo.RequestContext;
import java.io.IOException;
import okio.ByteString;

/**
 * Internal class that adapts an old {@link EntityCodec} to the new new {@link Codec} interface.
 */
class CodecAdapter implements Codec {

  private final EntityCodec delegate;

  CodecAdapter(EntityCodec delegate) {
    this.delegate = requireNonNull(delegate);
  }

  @Override
  public <E> EncodedResponse write(E entity, Class<? extends E> cls, RequestContext ctx)
      throws IOException {
    return EncodedResponse.create(delegate.write(entity, cls));
  }

  @Override
  public <E> E read(ByteString data, Class<? extends E> cls, RequestContext ctx)
      throws IOException {
    return delegate.read(data, cls);
  }
}
