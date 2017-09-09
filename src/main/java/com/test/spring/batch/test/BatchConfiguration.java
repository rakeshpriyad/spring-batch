package com.test.spring.batch.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
//@PropertySource(value={"file://C:/Users/aayushraj/Desktop/prnt/test.properties"})
//@PropertySource(value = "file:${CONF_DIR}/test.properties")
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<FieldSet> reader() {
        FlatFileItemReader<FieldSet> reader = new FlatFileItemReader<FieldSet>();
        reader.setResource(new ClassPathResource("sample-data2.csv"));
        reader.setLineMapper(new DefaultLineMapper<FieldSet>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
            	setFieldSetMapper(new PassThroughFieldSetMapper());
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessorImpl processor() {
        return new ItemProcessorImpl();
    }

    @Bean
    public FlatFileItemWriter<StringBuilder> writer() {
    	FlatFileItemWriter<StringBuilder> writer = new FlatFileItemWriter<StringBuilder>();
    	DelimitedLineAggregator<StringBuilder> aggregator = new DelimitedLineAggregator<StringBuilder>();
		aggregator.setDelimiter(",");
		writer.setLineAggregator(aggregator);
		writer.setResource(new FileSystemResource("C:\\Users\\aayushraj\\Desktop\\prnt\\test.csv"));
        return writer;
    }
    
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<FieldSet, StringBuilder> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}
