#! /usr/bin/env ruby

#USAGE: (ruby) test_jednostkowy_generator_danych_csv.rb "path/to/file.csv" columns rows

require 'csv'

$path = ARGV[0]
columns = ARGV[1].to_i
rows = ARGV[2].to_i

rowCounter = 0

result = -1

CSV.foreach($path) do |row|

	if row.size != columns+2
		result = rowCounter
		break
	end

	if row[0].to_i != rowCounter
		result = rowCounter
		break
	end

	unless row[1].size == 25
		result = rowCounter
		break
	end

	for i in 2...(row.size) do
		if row[i].to_i > 1 or row[i].to_i < 0
			result = rowCounter
			break
		end
	end
	rowCounter += 1
end


if result == -1
	p "ok"
else
	p result
end











