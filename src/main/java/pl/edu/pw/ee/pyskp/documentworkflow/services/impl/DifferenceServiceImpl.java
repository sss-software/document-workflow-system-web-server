package pl.edu.pw.ee.pyskp.documentworkflow.services.impl;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.Difference;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.DifferenceType;
import pl.edu.pw.ee.pyskp.documentworkflow.services.DifferenceService;
import pl.edu.pw.ee.pyskp.documentworkflow.services.TikaService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by piotr on 06.01.17.
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class DifferenceServiceImpl implements DifferenceService {

    private final TikaService tikaService;

    @Override
    public List<Difference> createDifferencesForNewFile(List<String> lines) {
        Patch<String> diff = DiffUtils.diff(Collections.emptyList(), lines);
        return diff.getDeltas().stream()
                .map(this::mapDeltaToDifference)
                .collect(Collectors.toList());
    }

    @Override
    public List<Difference> getDifferencesBetweenTwoFiles(List<String> previousVersionLines,
                                                          List<String> currentVersionLines) {
        Patch<String> diff = DiffUtils.diff(previousVersionLines, currentVersionLines);
        return diff.getDeltas().stream()
                .map(this::mapDeltaToDifference)
                .collect(Collectors.toList());
    }

    private Difference mapDeltaToDifference(Delta<String> delta) {
        Chunk<String> original = delta.getOriginal();
        Chunk<String> revised = delta.getRevised();
        Difference difference = new Difference();
        difference.setPreviousSectionStart(original.getPosition());
        difference.setPreviousSectionSize(original.size());
        difference.setNewSectionStart(revised.getPosition());
        difference.setNewSectionSize(revised.size());
        DifferenceType differenceType = DifferenceType.fromDeltaType(delta.getType())
                .orElseThrow(() -> new IllegalArgumentException("Delta type is null"));
        difference.setDifferenceType(differenceType);
        return difference;
    }

}
