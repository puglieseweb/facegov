CREATE TABLE content
(
  pageName varchar(255),
  pageSection integer,
  content TEXT,
  author varbinary (255),
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
  primary key (pageName, pageSection)
);

INSERT INTO content(pageName, pageSection, content)values
        ('Project Presentation', 1, 'This project is is distributed system using web services');
commit;

select * from content;