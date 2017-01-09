package pl.edu.pw.ee.pyskp.documentworkflow.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by piotr on 07.01.17.
 */
public class VersionInfoDTO {
    private long id;
    private UserInfoDTO author;
    private Date saveDate;
    private String versionString;
    private String message;
    private List<DifferenceInfoDTO> differences;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserInfoDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserInfoDTO author) {
        this.author = author;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setVersionString(String versionString) {
        this.versionString = versionString;
    }

    public String getVersionString() {
        return versionString;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DifferenceInfoDTO> getDifferences() {
        return differences;
    }

    public void setDifferences(List<DifferenceInfoDTO> differences) {
        this.differences = differences;
    }

    public int getNumberOfDifferences() {
        return differences == null ? 0 : differences.size();
    }

    public long getNumberOfChangedLines() {
        return differences.stream().mapToLong(DifferenceInfoDTO::getNewSectionSize).sum();
    }
}
