using Microsoft.EntityFrameworkCore.Migrations;

namespace FastFashion.Migrations
{
    public partial class changedAgeToDate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Age",
                table: "FashionItems");

            migrationBuilder.AddColumn<string>(
                name: "Date",
                table: "FashionItems",
                type: "nvarchar(max)",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Date",
                table: "FashionItems");

            migrationBuilder.AddColumn<int>(
                name: "Age",
                table: "FashionItems",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }
    }
}
