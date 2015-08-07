package com.appleframework.qos.server.statistics.mybatis2.dto;

import com.appleframework.qos.core.orm.PageQuery;
import com.appleframework.qos.server.statistics.mybatis2.dto.table.PagingCriteria;
import com.appleframework.qos.server.statistics.mybatis2.mvc.DataTablesResultSet;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * PageMyBatis.
 * </p>
 *
 * @author mumu @yfyang
 * @version 1.0 2013-08-03 9:31 PM
 * @since JDK 1.5
 */
public class PageMyBatis<E> extends ArrayList<E> {
    private static final long serialVersionUID = -3472924628671922516L;
    /**
     * data connection.
     */
    private final List<E> content = new ArrayList<E>(0);
    /**
     * pagination information
     */
    private final PageQuery pageable;
    /**
     * count resultset.
     */
    private final long total;

    /**
     * Instantiates a new Page my batis.
     *
     * @param content  the content
     * @param pageable the pageable
     * @param total    the total
     */
    public PageMyBatis(Collection<? extends E> content, PageQuery pageable, long total) {
        super(content);

        this.content.addAll(content);
        this.total = total;
        this.pageable = pageable;
    }

    /**
     * Instantiates a new Page my batis.
     *
     * @param content the content
     */
    public PageMyBatis(List<E> content) {
        // fixed total is 0 throw NullPointException
        this(content, null, null == content ? 0 : content.size());
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * Warp page.
     *
     * @return the page
     */
    public DataTablesResultSet<E> warp() {
        return new DataTablesResultSet<E>(pageable == null ? 0 : (int)pageable.getPage().getPageNo(), this);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("content", content)
                .add("pageable", pageable)
                .add("total", total)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        PageMyBatis that = (PageMyBatis) o;

        return total == that.total && !(content != null ? !content.equals(that.content) : that.content != null) && !(pageable != null ? !pageable.equals(that.pageable) : that.pageable != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (pageable != null ? pageable.hashCode() : 0);
        result = 31 * result + (int) (total ^ (total >>> 32));
        return result;
    }
}
