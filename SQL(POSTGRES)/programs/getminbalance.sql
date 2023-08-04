Create or replace function getMinimumBalance(ano int,strd date,endd date) returns numeric as $$
DECLARE 
	trans_cursor CURSOR FOR SELECT tr_type,amount from transaction where accno=ano and tr_date between strd and endd;
	tt char;
	amt numeric;
	cb numeric;
	mb numeric;
Begin
	select balance into cb from accounts where accno=ano;
	mb:=cb;
	OPEN trans_cursor;
	Loop
    	FETCH trans_cursor INTO tt,amt;
	  	Exit When not found;
if tt='D' then
			cb:=cb-amt;
    	elsif tt='W' then
  			cb:=cb+amt;
    	END If;
	if (cb < mb) then
		mb:=cb;
	end if;
	end loop;
	return mb;
	CLOSE trans_cursor;
End; $$ language plpgsql;
