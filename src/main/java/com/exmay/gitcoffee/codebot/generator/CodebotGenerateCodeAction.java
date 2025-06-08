/**
 * Copyright (c) 2025-2099 GitCoffee All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.exmay.gitcoffee.codebot.generator;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import com.intellij.ui.jcef.JBCefBrowser;

import javax.swing.*;
import java.util.Date;

public class CodebotGenerateCodeAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String codebotUrl = "https://codebot.exmay.com/";
        codebotUrl = "https://codebot.cloud.exmay.com/" + "?=" + new Date().getTime();
        CodebotJcefBrowser codebotBrowser = new CodebotJcefBrowser(codebotUrl);
        codebotBrowser.show();
    }

}