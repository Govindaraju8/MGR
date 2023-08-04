Create or replace function getBalanceAsOnDate(ano int,od date)
returns numeric as $$
DECLARE 
	trans_cursor CURSOR FOR SELECT tr_type,amount from transaction where accno=ano and 	tr_date > od;
	tt char;
	amt numeric;
	cb numeric;
Begin
	select balance into cb from accounts where accno=ano;
	OPEN trans_cursor;
	Loop
    	FETCH trans_cursor INTO tt,amt;
	  	Exit When not found;
  		if tt='D' then
			cb:=cb-amt;
    	elsif tt='W' then
  			cb:=cb+amt;
    	END If;
	end loop;
	return cb;
	CLOSE trans_cursor;
End; $$ language plpgsql;
