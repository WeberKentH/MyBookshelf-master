package com.monke.monkeybook.model.content;

import com.monke.monkeybook.bean.BookShelfBean;
import com.monke.monkeybook.bean.BookSourceBean;
import com.monke.monkeybook.model.analyzeRule.AnalyzeConfig;
import com.monke.monkeybook.model.analyzeRule.AnalyzerFactory;
import com.monke.monkeybook.model.analyzeRule.OutAnalyzer;

import io.reactivex.Observable;

final class BookInfo {
    private final OutAnalyzer<?> analyzer;

    BookInfo(String tag, String name, BookSourceBean bookSourceBean) {
        this.analyzer = AnalyzerFactory.create(bookSourceBean.getBookSourceRuleType(), new AnalyzeConfig()
                .tag(tag).name(name).bookSource(bookSourceBean));
    }

    Observable<BookShelfBean> analyzeBookInfo(String s, final BookShelfBean bookShelfBean) {
        analyzer.apply(analyzer.newConfig()
                .baseURL(bookShelfBean.getNoteUrl())
                .variableStore(bookShelfBean));
        return analyzer.getBook(s);
    }
}
