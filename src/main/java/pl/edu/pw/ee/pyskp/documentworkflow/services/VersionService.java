package pl.edu.pw.ee.pyskp.documentworkflow.services;

import org.bson.types.ObjectId;
import org.springframework.validation.annotation.Validated;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.FileMetadata;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.Version;
import pl.edu.pw.ee.pyskp.documentworkflow.dtos.file.NewFileForm;
import pl.edu.pw.ee.pyskp.documentworkflow.dtos.version.DiffData;
import pl.edu.pw.ee.pyskp.documentworkflow.dtos.version.NewVersionForm;
import pl.edu.pw.ee.pyskp.documentworkflow.dtos.version.VersionInfoDTO;
import pl.edu.pw.ee.pyskp.documentworkflow.exceptions.ResourceNotFoundException;
import pl.edu.pw.ee.pyskp.documentworkflow.exceptions.VersionNotFoundException;

import javax.validation.Valid;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by piotr on 06.01.17.
 */
@Validated
public interface VersionService {
    Version createInitVersionOfFile(NewFileForm form, FileMetadata fileMetadata);

    InputStream getVersionFileContent(ObjectId fileId, Date saveDate) throws VersionNotFoundException;

    long addNewVersionOfFile(@Valid NewVersionForm form) throws ResourceNotFoundException;

    DiffData buildDiffData(ObjectId fileId, long versionSaveDateMillis) throws VersionNotFoundException;

    VersionInfoDTO getVersionInfo(ObjectId fileId, long versionSaveDateMillis) throws VersionNotFoundException;

    boolean existsByVersionString(ObjectId fileId, String versionString);
}
