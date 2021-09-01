package com.yuudati.bean;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * @author Created by lixindong[lixindong@kuke.com].
 * @date 2021/9/1
 */
@Data
@AllArgsConstructor
public class Club implements Comparable<Club> {

    /**
     * 隶属于角色
     */
    private String sourceNickName;

    /**
     * 线索序号
     */
    private Integer club = 0;

    /**
     * 是否已交换
     */
    private boolean trans = false;

    /**
     * 交换目标
     */
    private String targetNickName;


    public Club(String sourceNickName, Integer club) {
        this.sourceNickName = sourceNickName;
        this.club = club;
    }

    public boolean isTrans() {
        return trans;
    }

    public Club setTrans(boolean trans) {
        this.trans = trans;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club1 = (Club) o;
        return Objects.equal(club, club1.club);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(club);
    }

    @Override
    public int compareTo(@NonNull Club o) {
        return Integer.compare(this.club, o.club);
    }
}
