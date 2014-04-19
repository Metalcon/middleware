package de.metalcon.middleware.domain.data;

import de.metalcon.like.api.Vote;

public class LikeData {

    private int upVoteNum;

    private int downVoteNum;

    private String currentVote;

    public int getUpVoteNum() {
        return upVoteNum;
    }

    public void setUpVoteNum(int upVoteNum) {
        this.upVoteNum = upVoteNum;
    }

    public int getDownVoteNum() {
        return downVoteNum;
    }

    public void setDownVoteNum(int downVoteNum) {
        this.downVoteNum = downVoteNum;
    }

    public String getCurrentVote() {
        if (currentVote == null) {
            return Vote.NEUTRAL.name();
        }
        return currentVote;
    }

    public void setCurrentVote(Vote currentVote) {
        if (currentVote == null) {
            currentVote = Vote.NEUTRAL;
        }
        this.currentVote = currentVote.name();
    }
}
