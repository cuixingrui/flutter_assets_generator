package com.netease.plugin.setting;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent mySettingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Flutter Assets Generator";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new AppSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        PluginSetting settings = PluginSetting.getInstance();
        boolean modified = !mySettingsComponent.getAssetsPath().equals(settings.assetsPath);
        modified |= mySettingsComponent.getAutoDetection() != settings.autoDetection;
        modified |= !mySettingsComponent.getClassName().equals(settings.className);
        modified |= !mySettingsComponent.getFilePath().equals(settings.filePath);
        modified |= mySettingsComponent.getNamedWithParent() != settings.namedWithParent;
        return modified;
    }

    @Override
    public void apply() {
        PluginSetting settings = PluginSetting.getInstance();
        settings.assetsPath = mySettingsComponent.getAssetsPath();
        settings.autoDetection = mySettingsComponent.getAutoDetection();
        settings.className = mySettingsComponent.getClassName();
        settings.filePath = mySettingsComponent.getFilePath();
        settings.namedWithParent = mySettingsComponent.getNamedWithParent();
    }

    @Override
    public void reset() {
        PluginSetting settings = PluginSetting.getInstance();
        mySettingsComponent.setAssetsPath(settings.assetsPath);
        mySettingsComponent.setAutoDetection(settings.autoDetection);
        mySettingsComponent.setClassName(settings.className);
        mySettingsComponent.setFilePath(settings.filePath);
        mySettingsComponent.setNamedWithParent(settings.namedWithParent);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }

}
