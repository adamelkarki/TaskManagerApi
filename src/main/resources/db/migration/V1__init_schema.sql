CREATE TABLE task (
                      id UUID PRIMARY KEY,
                      title VARCHAR(100) NOT NULL,
                      description VARCHAR(500),
                      status VARCHAR(20) NOT NULL,
                      priority VARCHAR(20) NOT NULL,
                      due_date DATE NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      updated_at TIMESTAMP NOT NULL
);
