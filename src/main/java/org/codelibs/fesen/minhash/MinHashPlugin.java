package org.codelibs.fesen.minhash;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fesen.index.analysis.TokenFilterFactory;
import org.codelibs.fesen.index.mapper.Mapper;
import org.codelibs.fesen.indices.analysis.AnalysisModule.AnalysisProvider;
import org.codelibs.fesen.minhash.index.analysis.MinHashTokenFilterFactory;
import org.codelibs.fesen.minhash.index.mapper.MinHashFieldMapper;
import org.codelibs.fesen.plugins.AnalysisPlugin;
import org.codelibs.fesen.plugins.MapperPlugin;
import org.codelibs.fesen.plugins.Plugin;

public class MinHashPlugin extends Plugin
        implements MapperPlugin, AnalysisPlugin {

    @Override
    public Map<String, AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        final Map<String, AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        extra.put("minhash", MinHashTokenFilterFactory::new);
        return extra;
    }

    @Override
    public Map<String, Mapper.TypeParser> getMappers() {
        return Collections.<String, Mapper.TypeParser> singletonMap(
                MinHashFieldMapper.CONTENT_TYPE,
                new MinHashFieldMapper.TypeParser());
    }
}
