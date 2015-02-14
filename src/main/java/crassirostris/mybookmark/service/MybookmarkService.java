package crassirostris.mybookmark.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by crassirostris on 15. 2. 7..
 */
@Service
public class MybookmarkService {
    @Autowired
    private CollectorCodeRepository codeRepository;
    @Autowired
    private BookmarkListRepository bookmarkListRepository;
    @Autowired
    private BookmarkItemsRepository bookmarkItemsRepository;
    @Autowired
    private CollectorCodeBookmarkRelationRepository collectorCodeBookmarkRelationRepository;
    @Autowired
    private BookmarkItemsCache itemsCache;


    public void insertCode(CollectorCode code) {
        codeRepository.save(code);
    }

    public void insertUrl(BookmarkList url) {
        bookmarkListRepository.save(url);
    }

    public List<BookmarkItems> selectGetUrls(String code) {
        List<CollectorCodeBookmarkRelation> lists = collectorCodeBookmarkRelationRepository.findAllByCollectorCode(code);

        if (CollectionUtils.isEmpty(lists)) {
            return Lists.newArrayList();
        }

        //Map<Long, CollectorCodeBookmarkRelation> relationMap = lists.stream().parallel().collect(Collectors.toMap(CollectorCodeBookmarkRelation::getListseq, (p) -> p));
        Map<Long, CollectorCodeBookmarkRelation> relationMap = lists.stream().collect(Collectors.toMap(CollectorCodeBookmarkRelation::getListseq, (p) -> p));

        int dateHourIndex = getDateHourIndex();
        List<BookmarkItems> items = bookmarkItemsRepository.findAllByDateHourIndex(dateHourIndex);
        //items.stream().parallel().filter((item) -> {
        List<BookmarkItems> collect = items.stream()
                .filter((item) -> relationMap.containsKey(item.getListSeq()))
                .sorted((s1, s2) -> s1.getCreatedAt().after(s2.getCreatedAt()) ? -1 : 1)
                .collect(Collectors.toList());
        //bookmarkItemsRepository.findAllBy
        return collect;
    }

    private int getDateHourIndex() {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHH");
        return Integer.parseInt(sdf.format(instance.getTime()));
    }
}
