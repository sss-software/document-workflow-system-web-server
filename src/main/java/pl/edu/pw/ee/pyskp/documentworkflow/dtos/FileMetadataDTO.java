package pl.edu.pw.ee.pyskp.documentworkflow.dtos;

import lombok.NonNull;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by piotr on 31.12.16.
 */
@Value
public class FileMetadataDTO {
    @NonNull
    String id, name;

    String description;

    @NonNull
    String contentType, extension;

    boolean confirmed, markedToConfirm;

    @NonNull
    OffsetDateTime creationDate, modificationDate;

    @NonNull
    VersionSummaryDTO latestVersion;

    int numberOfVersions;

    List<VersionInfoDTO> versions;
}