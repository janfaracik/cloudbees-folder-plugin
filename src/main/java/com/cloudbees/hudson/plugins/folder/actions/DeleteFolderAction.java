package com.cloudbees.hudson.plugins.folder.actions;

import com.cloudbees.hudson.plugins.folder.AbstractFolder;
import hudson.Extension;
import hudson.model.Action;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.action.DeleteAction;

import java.util.Collection;
import java.util.Set;

@Extension
public class DeleteFolderAction extends TransientActionFactory<AbstractFolder> {

    @Override
    public Class<AbstractFolder> type() {
        return AbstractFolder.class;
    }

    @Override
    public Collection<? extends Action> createFor(AbstractFolder target) {
        // todo fix this
        return Set.of(new DeleteAction("Folder", "to fix"));
    }
}

