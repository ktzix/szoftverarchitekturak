using Microsoft.EntityFrameworkCore.Migrations;

namespace FastFashion.Migrations
{
    public partial class useridAsFK : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "Id",
                table: "User",
                newName: "UserId");

            migrationBuilder.AddColumn<int>(
                name: "UserId",
                table: "FashionItems",
                type: "int",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_FashionItems_UserId",
                table: "FashionItems",
                column: "UserId");

            migrationBuilder.AddForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems",
                column: "UserId",
                principalTable: "User",
                principalColumn: "UserId",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems");

            migrationBuilder.DropIndex(
                name: "IX_FashionItems_UserId",
                table: "FashionItems");

            migrationBuilder.DropColumn(
                name: "UserId",
                table: "FashionItems");

            migrationBuilder.RenameColumn(
                name: "UserId",
                table: "User",
                newName: "Id");
        }
    }
}
