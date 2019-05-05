package Service;

import Mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Grade_Trem;
import pojo.Score;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int insertScore(Score score) {
        return scoreMapper.insertScore(score);
    }

    @Override
    public List<Score> queryScoreByStuId(Grade_Trem grade_trem) {
        return scoreMapper.queryScoreByStuId(grade_trem);
    }
}
