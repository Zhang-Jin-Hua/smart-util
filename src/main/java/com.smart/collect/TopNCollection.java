package com.smart.collect;

import java.util.*;

/**
 * 该集合用于取出前N个元素
 *
 * @param <T>
 */

public class TopNCollection<T> {
    /**
     * 存放top N包含的数据
     */
    private List<T> elements;
    /**
     * 比较器
     */
    private Comparator<T> comparable;
    /**
     * 该集合包含前多少的元素
     */
    private int topN;

    public TopNCollection(int topN, Comparator<T> comparable) {
        Objects.requireNonNull(topN > 0, "N 必须大于0");
        Objects.requireNonNull(comparable != null, "comparable不能为空");

        elements = new ArrayList<>(5 + topN + (topN / 10));
        this.comparable = comparable;
        this.topN = topN;
    }

    public List<T> getTopN() {
        return Collections.unmodifiableList(elements);
    }

    public void put(T t) {
        keepTopN(t);
    }

    private void keepTopN(T t) {
        if (elements.size() == 0) {
            elements.add(t);
            return;
        }

        boolean isModify = false;

        if (elements.size() < topN) {
            elements.add(t);
            isModify = true;
        }

        if (comparable.compare(elements.get(elements.size() - 1), t) > 0) {
            elements.set(elements.size() - 1, t);
            isModify = true;
        }

        if (!isModify) {
            return;
        }

        for (int index = elements.size() - 1; index > 0; index--) {
            T element = elements.get(index);
            T pre = elements.get(index - 1);

            if (comparable.compare(pre, element) <= 0) {
                break;
            }

            //swap
            elements.set(index, pre);
            elements.set(index - 1, element);
        }
    }
}
