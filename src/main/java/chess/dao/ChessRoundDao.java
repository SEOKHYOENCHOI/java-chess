package chess.dao;

import chess.domain.Position;
import chess.dto.ChessBoardDto;
import chess.dto.ChessInfoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessRoundDao {
    private static ChessRoundDao chessRoundDao;

    public static ChessRoundDao getInstance() {
        if (Objects.isNull(chessRoundDao)) {
            chessRoundDao = new ChessRoundDao();
        }
        return chessRoundDao;
    }

    public void insertChessInfoByRoundId(int round, ChessInfoDto chessInfoDto) {
        String query = "INSERT INTO chess_log (round, turn, source, target) VALUES (?,?,?,?)";

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();

        queryValues.add(round);
        queryValues.add(chessInfoDto.getTurn());
        queryValues.add(chessInfoDto.getSource());
        queryValues.add(chessInfoDto.getTarget());

        jdbcTemplate.executeUpdate(query, queryValues);
    }

    public List<ChessInfoDto> selectChessInfoByRoundId(int roundId) {
        String query = "SELECT turn, source, target FROM chess_log WHERE round = ?";

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();

        queryValues.add(roundId);

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        List<ChessInfoDto> chessInfoDtos = new ArrayList<>();

        for (Map<String, Object> result : results) {
            String turn = (String) result.get("turn");
            String source = (String) result.get("source");
            String target = (String) result.get("target");

            chessInfoDtos.add(new ChessInfoDto(turn, source, target));
        }
        System.out.println(chessInfoDtos);
        return chessInfoDtos;
    }

    public int selectTurnByRoundId(int roundId) {
        String query = "SELECT turn FROM chess_log WHERE round = ? ORDER BY id DESC";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(roundId);

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (results.size() < 1) {
            return 0;
        }

        Map<String, Object> result = results.get(0);
        return Integer.parseInt((String) result.get("turn"));
    }

    public void updateTurnByRoundId(int roundId, int turn) {

        int changedTurn = (turn + 1) % 2;
        String query = "UPDATE chess_log SET ? WHERE roundId = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(turn);
        queryValues.add(roundId);

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        jdbcTemplate.executeUpdate(query, queryValues);
    }
}