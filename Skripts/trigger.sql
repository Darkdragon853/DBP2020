-- first create the new table for the documentation of the delete process
CREATE TABLE IF NOT EXISTS deletedWorkers (
	deletedOn    timestamp NOT NULL, 
	person_id    bigint    NOT NULL,
      	company_id   bigint    NOT NULL	
);

-- function that writes a timestamp and the row to delete into the new table
-- 
CREATE FUNCTION saveEndOfWork() RETURNS trigger AS $endOfWork$
BEGIN    
	INSERT INTO del SELECT now(), OLD.person_id, OLD.compny.id;
	RETURN OLD;
END;
$endOfWork$ LANGUAGE plpgsql;

CREATE TRIGGER endOfWork BEFORE DELETE 
ON person_workat_company
   FOR EACH ROW  
    EXECUTE PROCEDURE saveEndOfWork();

