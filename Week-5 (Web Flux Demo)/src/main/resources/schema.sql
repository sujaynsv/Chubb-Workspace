-- Drop table if exists
DROP TABLE IF EXISTS tutorial;

-- Create tutorial table
CREATE TABLE IF NOT EXISTS tutorial (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    published BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
