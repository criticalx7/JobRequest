package client.ui.model;

import client.controller.ActionController;
import client.controller.JobAdapter;
import common.formatter.DateFormatter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Project Name: JobRequest
 */
public class ReviewerMasterModel {

    private final ActionController handler;
    private final ObservableList<JobAdapter> jobList = FXCollections.observableArrayList();
    private final ObjectProperty<JobAdapter> currentJobs = new SimpleObjectProperty<>(null);
    private final DateFormatter dateFormatter = new DateFormatter();

    public ReviewerMasterModel(ActionController handler) {
        this.handler = handler;
        refreshList();
    }

    public void reviewAndSend(String status) {
        JobAdapter current = getCurrentAdapter();
        current.statusProperty().set(status);
        current.save();
        handler.handleEdit(currentJobs.get().getModel());
    }


    public void refreshList() {
        jobList.clear();
        handler.handleLoadJobs().forEach(job -> jobList.add(new JobAdapter(job)));
    }

    public ObservableList<JobAdapter> getJobList() {
        return jobList;
    }


    public JobAdapter getCurrentAdapter() {
        return currentJobs.get();
    }

    public void setCurrentAdapter(JobAdapter currentJob) {
        this.currentJobs.set(currentJob);
    }

    public ObjectProperty<JobAdapter> currentAdapterProperty() {
        return currentJobs;
    }

    public DateFormatter getDateFormatter() {
        return dateFormatter;
    }

}
