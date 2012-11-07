#! /usr/bin/env ruby

#USAGE: (ruby) generator_danych_csv.rb "path/to/file.csv" columns rows

require 'csv'

$path = ARGV[0]

CSV.open($path, "wb") do |file|

	columns = ARGV[1].to_i
	rows = ARGV[2].to_i
	
	time = Time.new

	rows.times do |c|
		row = [c.to_s]
		row << time.inspect.to_s

		columns.times do
			row << rand().to_s
		end

		file << row
	end
end












