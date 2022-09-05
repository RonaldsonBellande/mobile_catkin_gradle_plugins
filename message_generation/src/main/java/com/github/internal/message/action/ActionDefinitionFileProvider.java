/*
 * Copyright (C) 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.internal.message.action;

import com.github.internal.message.definition.MessageDefinitionFileProvider;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import com.github.internal.message.StringFileProvider;

import java.io.File;
import java.io.FileFilter;

/**
 * @author arne.peters@tum.de (Arne Peters)
 */
public class ActionDefinitionFileProvider extends MessageDefinitionFileProvider {

  private static final String PARENT = "action";
  private static final String SUFFIX = "action";

  private static StringFileProvider newStringFileProvider() {
    IOFileFilter extensionFilter = FileFilterUtils.suffixFileFilter(SUFFIX);
    IOFileFilter parentBaseNameFilter = FileFilterUtils.asFileFilter(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return getParentBaseName(file.getAbsolutePath()).equals(PARENT);
      }
    });
    IOFileFilter fileFilter = FileFilterUtils.andFileFilter(extensionFilter, parentBaseNameFilter);
    return new StringFileProvider(fileFilter);
  }

  public ActionDefinitionFileProvider() {
    super(newStringFileProvider());
  }
}
