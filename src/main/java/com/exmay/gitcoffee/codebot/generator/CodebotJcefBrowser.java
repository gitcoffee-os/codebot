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

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.jcef.*;
import org.cef.handler.*;
import com.jetbrains.cef.JCefAppConfig;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CodebotJcefBrowser extends DialogWrapper {

    private final String url;

    public CodebotJcefBrowser(String url) {
        super(true);
        this.url = url;
        init();
        setTitle("CodeBot 代码生成助手");
    }

    @Override
    protected void init() {
        super.init();
        // 设置初始化大小
        setSize(1024, 768);  // 宽度 1024，高度 768
    }

    @Override
    protected JComponent createSouthPanel() {
        // 返回一个空的 JPanel 来隐藏默认的取消和确定按钮
        return new JPanel();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        // 创建JCEF浏览器实例并加载URL
        JBCefBrowser jbCefBrowser = new JBCefBrowser();
        jbCefBrowser.loadURL(url);

        // 监听表单提交（示例：通过JavaScript与Java交互）
        jbCefBrowser.getJBCefClient().addLoadHandler(new CefLoadHandlerAdapter() {
            @Override
            public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {
                String url = browser.getURL();
                if (url.contains("codebot")) {
                    browser.executeJavaScript(
                            "document.getElementById('app').innerText",
                            url, 0);
                }
            }
        }, jbCefBrowser.getCefBrowser());

        return jbCefBrowser.getComponent();
    }

    public static void main(String[] args) {
        // 示例用法
        CodebotJcefBrowser codebotBrowser = new CodebotJcefBrowser("https://codebot.cloud.exmay.com/");
        codebotBrowser.show();
    }
}