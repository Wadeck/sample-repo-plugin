package io.jenkins.plugins.samplerepo;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.verb.POST;

import java.io.IOException;
import java.net.URL;

public class SampleBuildStep extends Builder {

    private final String messageToDisplay;

    @DataBoundConstructor
    public SampleBuildStep(String messageToDisplay) {
        this.messageToDisplay = messageToDisplay;
    }

    public String getMessageToDisplay() {
        return messageToDisplay;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        listener.getLogger().println(messageToDisplay);
        listener.getLogger().println(this.getDescriptor().getGlobalMessageToDisplay());
        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    @Symbol("sample")
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        private String globalMessageToDisplay;

        public String getGlobalMessageToDisplay() {
            return globalMessageToDisplay;
        }

        public void setGlobalMessageToDisplay(String globalMessageToDisplay) {
            this.globalMessageToDisplay = globalMessageToDisplay;
        }

        // lgtm[jenkins/csrf]
        public FormValidation doCheckGlobalMessageToDisplay1_notBefore(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @SuppressWarnings("lgtm[jenkins/csrf]")
        public FormValidation doCheckGlobalMessageToDisplay2_ok(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        // lgtm[jenkins/no-permission-check]
        @SuppressWarnings("lgtm[jenkins/csrf]")
        public FormValidation doCheckGlobalMessageToDisplay3_notBefore(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckGlobalMessageToDisplay4_noSuppressions(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @SuppressWarnings("lgtm[jenkins/no-permission-check]")
        public FormValidation doCheckGlobalMessageToDisplay5_suppressed(@QueryParameter String value) throws IOException { // lgtm[jenkins/csrf]
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @SuppressWarnings("lgtm[jenkins/no-permission-check]")
        public FormValidation doCheckGlobalMessageToDisplay6_notAfter(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        } // lgtm[jenkins/csrf]

        @SuppressWarnings("lgtm")
        public FormValidation doCheckGlobalMessageToDisplay7_onlySupportedInComment(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        // lgtm
        public FormValidation doCheckGlobalMessageToDisplay8_endOfLineOnly(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @SuppressWarnings("lgtm[]")
        public FormValidation doCheckGlobalMessageToDisplay9_onlySupportedInComment(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckGlobalMessageToDisplay10_suppressed(@QueryParameter String value) throws IOException { // lgtm
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @POST
        public FormValidation doCheckGlobalMessageToDisplay11_ok(@QueryParameter String value) throws IOException {
            Jenkins.get().checkPermission(Jenkins.ADMINISTER);
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @SuppressWarnings({"lgtm[jenkins/no-permission-check] with rationale","lgtm[jenkins/csrf]"})
        public FormValidation doCheckGlobalMessageToDisplay12_suppressed(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject data) throws FormException {
            req.bindJSON(this, data);
            return super.configure(req, data);
        }
    }
}
